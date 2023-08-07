package com.project.githering.Group.Service;

import com.project.githering.Group.DTO.CreateGroupRequestDTO;
import com.project.githering.Group.DTO.UpdateGroupInformRequestDTO;
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
    boolean isMaster(Long userId, Long groupId);

    boolean hasMasterAuthority(Long userId, Long groupId);


    boolean existById(Long groupId);

    Optional<Group> findGroupById(Long groupId);

    Optional<Group> findGroupByName(String groupName);

    Integer findCount(Long groupId);

    Page<Group> findAllGroup(Pageable pageable);



    //UPDATE
    void updateInform(Long userId, UpdateGroupInformRequestDTO updateGroupInformRequestDTO);
}
