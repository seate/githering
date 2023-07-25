package com.solution.loginSolution.User.General.Service;

import com.solution.loginSolution.JWT.Repository.LogoutRedisRepository;
import com.solution.loginSolution.JWT.Repository.RefreshTokenRedisRepository;
import com.solution.loginSolution.JWT.Service.LogoutAccessTokenService;
import com.solution.loginSolution.JWT.Service.RefreshTokenService;
import com.solution.loginSolution.User.General.Repository.GeneralUserRepository;
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
    void register() {
        /*UserRegisterRequestDTO userRegisterRequestDTO = new UserRegisterRequestDTO("admin@gmail.com", "admin");
        String userEmail = userRegisterRequestDTO.getUserEmail();

        generalUserService.register(userRegisterRequestDTO);

        Assertions.assertTrue(generalUserRepository.existsByUserEmail(userEmail));*/
    }

    @Test
    void withdrawal() {
        /*UserRegisterRequestDTO userRegisterRequestDTO = new UserRegisterRequestDTO("admin@gmail.com", "admin");
        generalUserService.register(userRegisterRequestDTO);

        String userEmail = userRegisterRequestDTO.getUserEmail();
        Long userId = generalUserService.findIdByUserEmail(userEmail);

        generalUserService.withdrawal(userId);
        Assertions.assertFalse(generalUserRepository.existsById(userId));

        Assertions.assertThrows(
                RefreshTokenNotExistException.class,
                () -> refreshTokenService.findByUserEmail(userEmail)
        );*/
    }
}