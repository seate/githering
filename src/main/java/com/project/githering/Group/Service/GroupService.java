package com.project.githering.Group.Service;

import com.project.githering.Group.DTO.CreateGroupRequestDTO;
import com.project.githering.Group.DTO.UpdateGroupInformRequestDTO;
import com.project.githering.Group.DTO.UpdateGroupMasterRequestDTO;
import com.project.githering.Group.Entity.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface GroupService {

    //CREATE
    void createGroup(Long userId, CreateGroupRequestDTO createGroupRequestDTO);

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
    void updateMaster(Long userId, UpdateGroupMasterRequestDTO updateGroupMasterRequestDTO);

    void updateInform(Long userId, UpdateGroupInformRequestDTO updateGroupInformRequestDTO);
}
