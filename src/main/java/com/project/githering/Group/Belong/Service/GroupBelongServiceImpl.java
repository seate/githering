package com.project.githering.Group.Belong.Service;

import com.project.githering.Group.Belong.Entity.GroupBelong;
import com.project.githering.Group.Belong.Exception.AlreadyJoinedException;
import com.project.githering.Group.Belong.Exception.NotJoinedException;
import com.project.githering.Group.Belong.Repository.GroupBelongRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupBelongServiceImpl implements GroupBelongService {

    private final GroupBelongRepository groupBelongRepository;

    @Override
    public void joinGroup(Long userId, Long groupId) throws AlreadyJoinedException {
        if(isJoined(userId, groupId)) throw new AlreadyJoinedException();

        groupBelongRepository.save(new GroupBelong(groupId, userId));
    }

    @Override
    public void withdrawalGroup(Long userId, Long groupId) throws NotJoinedException {
        if (!isJoined(userId, groupId)) throw new NotJoinedException();

        groupBelongRepository.delete(new GroupBelong(groupId, userId));
    }

    @Override
    public void withdrawalAllByGroupId(Long groupId) {
        groupBelongRepository.deleteAllByGroupId(groupId);
    }

    @Override
    public boolean isJoined(Long userId, Long groupId) {
        return groupBelongRepository.existsByUserIdAndGroupId(userId, groupId);
    }

    @Override
    public Integer countByGroupId(Long groupId) {
        return groupBelongRepository.countByGroupId(groupId);
    }
}
