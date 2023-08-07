package com.project.githering.Group.Service;

import com.project.githering.Group.Belong.Exception.AlreadyJoinedException;
import com.project.githering.Group.Belong.Repository.GroupBelongRepository;
import com.project.githering.Group.Belong.Service.GroupBelongService;
import com.project.githering.Group.DTO.CreateGroupRequestDTO;
import com.project.githering.Group.Entity.Group;
import com.project.githering.Group.Enum.GroupType;
import com.project.githering.Group.Exception.GroupNotExistException;
import com.project.githering.Group.Exception.NoAuthorityException;
import com.project.githering.Group.Repository.GroupRepository;
import com.project.githering.User.General.Entity.GeneralUser;
import com.project.githering.User.General.Entity.Role;
import com.project.githering.User.General.Repository.GeneralUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GroupServiceImplTest {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupService groupService;


    @Autowired
    private GroupBelongRepository groupBelongRepository;

    @Autowired
    private GroupBelongService groupBelongService;


    @Autowired
    private GeneralUserRepository generalUserRepository;


    @BeforeEach
    void setUp() {
        groupRepository.deleteAll();
        groupBelongRepository.deleteAll();
        generalUserRepository.deleteAll();
    }



    @Test
    void 전체_테스트() {
        Long userId1 = saveUser(1L, "loginUser11", "userName11", Role.ROLE_MEMBER);
        Long userId2 = saveUser(2L, "loginUser22", "userName22", Role.ROLE_ADMIN);
        Long userId3 = saveUser(3L, "loginUser33", "userName33", Role.ROLE_MEMBER);

        // 그룹 생성
        saveGroup(userId1, GroupType.UNIVERSITY, "groupName11", "groupDescription11");
        saveGroup(userId2, GroupType.ENTERPRISE, "groupName22", "groupDescription22");

        //TODO getMyGroups
        Group group1 = groupService.findGroupById(1L).orElseThrow(GroupNotExistException::new);
        Group group2 = groupService.findGroupById(2L).orElseThrow(GroupNotExistException::new);

        assertThrows(
                AlreadyJoinedException.class,
                () -> groupService.joinGroup(userId1, group1.getGroupId())
        );

        assertEquals(group1.getGroupMasterId(), userId1);

        assertTrue(groupBelongService.isJoined(userId1, group1.getGroupId()));
        assertFalse(groupBelongService.isJoined(userId1, group2.getGroupId()));

        groupService.joinGroup(userId3, group1.getGroupId());
        assertThrows(
                NoAuthorityException.class,
                () -> groupService.withdrawalGroup(userId1, group1.getGroupId())
        );




    }

    private void saveGroup(Long userId, GroupType groupType, String groupName, String groupDescription) {
        groupService.createGroup(userId, new CreateGroupRequestDTO(groupType, groupName, groupDescription));
    }

    private Long saveUser(Long userId, String loginUser, String userName, Role role) {
        GeneralUser generalUser = new GeneralUser(userId, loginUser, userName, role);
        generalUserRepository.save(generalUser);
        return userId;
    }

}