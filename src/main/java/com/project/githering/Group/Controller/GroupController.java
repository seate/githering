package com.project.githering.Group.Controller;

import com.project.githering.Group.DTO.*;
import com.project.githering.Group.Entity.Group;
import com.project.githering.Group.Exception.GroupNotExistException;
import com.project.githering.Group.Service.GroupService;
import com.project.githering.User.General.Service.GeneralUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/group")
public class GroupController {

    private final GeneralUserService generalUserService;

    private final GroupService groupService;


    //CREATE
    @PostMapping
    public ResponseEntity<Long> createGroup(@RequestBody @Valid CreateGroupRequestDTO createGroupRequestDTO) {
        Long userId = generalUserService.findIdByAuthentication();
        return new ResponseEntity<>(groupService.createGroup(userId, createGroupRequestDTO), HttpStatus.CREATED);
    }

    @PostMapping("/join")
    public ResponseEntity<Void> joinGroup(@RequestBody @Valid JoinGroupRequestDTO joinGroupRequestDTO) {
        Long userId = generalUserService.findIdByAuthentication();
        groupService.joinGroup(userId, joinGroupRequestDTO.getGroupId());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //DELETE
    @DeleteMapping
    public ResponseEntity<Void> deleteGroup(@RequestBody @Valid DeleteGroupRequestDTO deleteGroupRequestDTO) {
        Long userId = generalUserService.findIdByAuthentication();
        groupService.deleteGroupById(userId, deleteGroupRequestDTO.getGroupId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/withdrawal")
    public ResponseEntity<Void> withdrawalGroup(@RequestBody @Valid WithdrawalGroupRequestDTO withdrawalGroupRequestDTO) {
        Long userId = generalUserService.findIdByAuthentication();
        groupService.withdrawalGroup(userId, withdrawalGroupRequestDTO.getGroupId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //READ
    @GetMapping
    public ResponseEntity<List<GroupInformResponseDTO>> findAllGroup(Pageable pageable) {
        List<GroupInformResponseDTO> groupList = groupService
                .findAllGroup(pageable)
                .map(GroupInformResponseDTO::new)
                .toList();

        return new ResponseEntity<>(groupList, HttpStatus.OK);
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<GroupInformResponseDTO> findGroupById(@PathVariable Long groupId) {
        Group findGroup = groupService.findGroupById(groupId).orElseThrow(GroupNotExistException::new);
        return new ResponseEntity<>(new GroupInformResponseDTO(findGroup), HttpStatus.OK);
    }

    //UPDATE
    @PatchMapping("/inform")
    public ResponseEntity<Void> updateInform(@RequestBody @Valid UpdateGroupInformRequestDTO updateGroupInformRequestDTO) {
        Long userId = generalUserService.findIdByAuthentication();
        groupService.updateInform(
                userId,
                updateGroupInformRequestDTO
        );

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

