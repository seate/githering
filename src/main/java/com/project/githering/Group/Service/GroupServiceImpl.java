package com.project.githering.Group.Service;

import com.project.githering.Group.Belong.Exception.AlreadyJoinedException;
import com.project.githering.Group.Belong.Service.GroupBelongService;
import com.project.githering.Group.DTO.CreateGroupRequestDTO;
import com.project.githering.Group.DTO.UpdateGroupInformRequestDTO;
import com.project.githering.Group.Entity.Group;
import com.project.githering.Group.Exception.GroupExistException;
import com.project.githering.Group.Exception.GroupNotExistException;
import com.project.githering.Group.Exception.NoAuthorityException;
import com.project.githering.Group.Repository.GroupRepository;
import com.project.githering.User.General.Exception.UserNotExistException;
import com.project.githering.User.General.Service.GeneralUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    private final GroupBelongService groupBelongService;

    private final GeneralUserService generalUserService;


    //CREATE
    @Override
    @Transactional
    public Long createGroup(Long userId, CreateGroupRequestDTO createGroupRequestDTO) throws GroupExistException, GroupNotExistException {
        Group group = createGroupRequestDTO.toEntity(userId);

        findGroupByName(group.getGroupName()).ifPresent(g -> {throw new GroupExistException();});

        Long groupId = groupRepository.save(group).getGroupId();
        joinGroup(group.getGroupMasterId(), groupId);
        return groupId;
    }

    @Override
    @Transactional
    public void joinGroup(Long userId, Long groupId) throws AlreadyJoinedException, GroupNotExistException {
        findGroupById(groupId).ifPresentOrElse(
            group -> {
                if (groupBelongService.countByGroupId(groupId) == 0) // 그룹에 아무도 없을 경우(create 직후 or 전부 탈퇴했을 경우)
                    group.setGroupMasterId(userId); // 그룹장을 자신으로 설정
                groupBelongService.joinGroup(userId, group.getGroupId());
            },
            () -> {
                throw new GroupNotExistException();
            }
        );
    }



    //DELETE
    @Override
    @Transactional
    public void deleteGroupById(Long userId, Long groupId) throws GroupNotExistException, NoAuthorityException {
        if (!existById(groupId)) throw new GroupNotExistException();

        if (isMaster(userId, groupId)) { // 그룹장이면
            if (findCount(groupId) != 1) //TODO 나중에 로직 더 생각해서 수정
                throw new NoAuthorityException("그룹원이 한 명이 아니면 그룹을 삭제할 수 없습니다.");
        }
        else if (!generalUserService.isAdmin(userId)) // admin이 아니면
            throw new NoAuthorityException("그룹을 삭제할 권한이 없습니다.");

        // 그룹 탈퇴 처리
        groupBelongService.withdrawalAllByGroupId(groupId);

        groupRepository.deleteById(groupId);
    }

    @Override
    @Transactional
    public void withdrawalGroup(Long userId, Long groupId) throws GroupNotExistException, NoAuthorityException {
        Group findGroup = findGroupById(groupId).orElseThrow(GroupNotExistException::new);

        // 그룹 마스터이면서 그룹의 마지막 한 명이 아닐 경우
        if (findGroup.getGroupMasterId().equals(userId) && groupBelongService.countByGroupId(groupId) != 1)
            throw new NoAuthorityException("그룹원이 한 명이 아니면 그룹장은 그룹을 탈퇴할 수 없습니다.");

        groupBelongService.withdrawalGroup(userId, groupId);
    }



    //READ
    @Override
    public boolean isMaster(Long userId, Long groupId) {
        return findGroupById(groupId)
                .orElseThrow(GroupNotExistException::new)
                .getGroupMasterId().equals(userId);
    }

    @Override
    public boolean hasMasterAuthority(Long userId, Long groupId) throws GroupNotExistException {
        return (isMaster(userId, groupId) || generalUserService.isAdmin(userId));
    }


    @Override
    public boolean existById(Long groupId) {
        return groupRepository.existsById(groupId);
    }

    @Override
    public Optional<Group> findGroupById(Long groupId) {
        return groupRepository.findById(groupId);
    }

    @Override
    public Optional<Group> findGroupByName(String groupName) {
        return groupRepository.findByGroupName(groupName);
    }

    @Override
    public Integer findCount(Long groupId) throws GroupNotExistException {
        if (!existById(groupId)) throw new GroupNotExistException();

        return groupBelongService.countByGroupId(groupId);
    }

    @Override
    public Page<Group> findAllGroup(Pageable pageable) {
        return groupRepository.findAll(pageable);
    }



    //UPDATE
    @Override
    @Transactional
    public void updateInform(Long userId, UpdateGroupInformRequestDTO updateGroupInformRequestDTO)
            throws GroupNotExistException, NoAuthorityException, UserNotExistException {

        Long groupId = updateGroupInformRequestDTO.getGroupId();
        if (!hasMasterAuthority(userId, groupId)) throw new NoAuthorityException();

        Long newGroupMasterId = updateGroupInformRequestDTO.getNewGroupMasterId();
        generalUserService.findById(newGroupMasterId).orElseThrow(UserNotExistException::new);
        if (!groupBelongService.isJoined(newGroupMasterId, groupId))
            throw new NoAuthorityException("그룹원이 아닌 사용자를 그룹장으로 설정할 수 없습니다.");

        Group findGroup = groupRepository.findById(groupId).orElseThrow(GroupNotExistException::new);
        findGroup.setGroupMasterId(newGroupMasterId);
        findGroup.setGroupType(updateGroupInformRequestDTO.getGroupType());
        findGroup.setGroupName(updateGroupInformRequestDTO.getGroupName());
        findGroup.setGroupDescription(updateGroupInformRequestDTO.getGroupDescription());
    }
}
