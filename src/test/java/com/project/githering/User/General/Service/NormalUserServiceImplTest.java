package com.project.githering.User.General.Service;

import com.project.githering.JWT.Repository.LogoutRedisRepository;
import com.project.githering.JWT.Repository.RefreshTokenRedisRepository;
import com.project.githering.JWT.Service.LogoutAccessTokenService;
import com.project.githering.JWT.Service.RefreshTokenService;
import com.project.githering.User.General.Entity.GeneralUser;
import com.project.githering.User.General.Entity.Role;
import com.project.githering.User.General.Exception.UserNotExistException;
import com.project.githering.User.General.Repository.GeneralUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NormalUserServiceImplTest {

    @Autowired
    private GeneralUserRepository generalUserRepository;
    @Autowired
    private GeneralUserService generalUserService;

    @Autowired
    private RefreshTokenRedisRepository refreshTokenRedisRepository;
    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private LogoutRedisRepository logoutRedisRepository;
    @Autowired
    private LogoutAccessTokenService logoutAccessTokenService;

    @BeforeEach
    void setUp() {
        generalUserRepository.deleteAll();
        refreshTokenRedisRepository.deleteAll();
        logoutRedisRepository.deleteAll();
    }

    @Test
    void findIdByLoginUser() {
        GeneralUser generalUser = new GeneralUser(1L, "seate", "kimsi", Role.ROLE_MEMBER);
        generalUserRepository.save(generalUser);

        Long userId = generalUserService.findByLoginUser(generalUser.getLoginUser()).orElseThrow(UserNotExistException::new).getId();
    }
}