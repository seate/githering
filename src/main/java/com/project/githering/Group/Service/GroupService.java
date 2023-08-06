package com.project.githering.Group.Service;

import com.project.githering.Group.Entity.Group;
import com.project.githering.Group.Enum.GroupType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface GroupService {

    //CREATE
    void createGroup(Group group);

    void joinGroup(Long userId, Long groupId);

    //DELETE
    void deleteGroupById(Long userId, Long groupId);


    void withdrawalGroup(Long userId, Long groupId);

    //READ
    Optional<Group> findGroupByName(String groupName);

    Optional<Group> findGroupById(Long groupId);

    Integer findCount(Long groupId);

    Page<Group> findAllGroup(Pageable pageable);

    //UPDATE
    void updateMaster(Long groupId, Long masterId, Long newMasterId);

    void updateInform(Long userId, Long groupId, GroupType groupType, String groupName, String groupDescription);
}
