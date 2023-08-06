package com.project.githering.Group.Service;

import com.project.githering.Group.Entity.Group;
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
    //void updateGroup(Group group);
}
