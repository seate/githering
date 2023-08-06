package com.project.githering.Group.Service;

import com.project.githering.Group.Belong.Service.GroupBelongService;
import com.project.githering.Group.Entity.Group;
import com.project.githering.Group.Enum.GroupType;
import com.project.githering.Group.Exception.GroupExistException;
import com.project.githering.Group.Exception.GroupNotExistException;
import com.project.githering.Group.Exception.NoAuthorityException;
import com.project.githering.Group.Repository.GroupRepository;
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

    @Override
    @Transactional
    public void createGroup(Group group) throws GroupExistException, GroupNotExistException {
        findGroupByName(group.getGroupName()).ifPresentOrElse(
                g -> {throw new GroupExistException();},
                () -> {
                    groupRepository.save(group);
                    Long groupId = findGroupByName(group.getGroupName())
                            .orElseThrow(GroupNotExistException::new).getGroupId();
                    joinGroup(group.getGroupMasterId(), groupId);
                }
        );
    }

    @Override
    @Transactional
    public void joinGroup(Long userId, Long groupId) throws GroupNotExistException {
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


    @Override
    @Transactional
    public void deleteGroupById(Long userId, Long groupId) throws GroupNotExistException, NoAuthorityException {
        if (!existById(groupId)) throw new GroupNotExistException();
        if (!isGroupMaster(userId, groupId)) throw new NoAuthorityException();

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



    private boolean isGroupMaster(Long userId, Long groupId) throws GroupNotExistException {
        return findGroupById(groupId).orElseThrow(GroupNotExistException::new).getGroupMasterId().equals(userId);
    }

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

    @Override
    @Transactional
    public void updateMaster(Long groupId, Long masterId, Long newMasterId) throws GroupNotExistException, NoAuthorityException {
        Group findGroup = groupRepository.findById(groupId).orElseThrow(GroupNotExistException::new);
        if (!isGroupMaster(findGroup.getGroupId(), masterId)) throw new NoAuthorityException();

        findGroup.setGroupMasterId(newMasterId);
    }

    @Override
    public void updateInform(Long userId, Long groupId, GroupType groupType, String groupName, String groupDescription) throws GroupNotExistException, NoAuthorityException {
        if (!isGroupMaster(userId, groupId)) throw new NoAuthorityException();

        Group findGroup = groupRepository.findById(groupId).orElseThrow(GroupNotExistException::new);
        findGroup.setGroupType(groupType);
        findGroup.setGroupName(groupName);
        findGroup.setGroupDescription(groupDescription);
    }
}
