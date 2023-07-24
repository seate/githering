package com.solution.loginSolution.User.Normal.Service;

import com.solution.loginSolution.JWT.Exception.RefreshTokenNotExistException;
import com.solution.loginSolution.JWT.Repository.LogoutRedisRepository;
import com.solution.loginSolution.JWT.Repository.RefreshTokenRedisRepository;
import com.solution.loginSolution.JWT.Service.LogoutAccessTokenService;
import com.solution.loginSolution.JWT.Service.RefreshTokenService;
import com.solution.loginSolution.User.Normal.DTO.UserRegisterRequestDTO;
import com.solution.loginSolution.User.Normal.Repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

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
        userRepository.deleteAll();
        refreshTokenRedisRepository.deleteAll();
        logoutRedisRepository.deleteAll();
    }

    @Test
    void register() {
        UserRegisterRequestDTO userRegisterRequestDTO = new UserRegisterRequestDTO("admin@gmail.com", "admin");
        String userEmail = userRegisterRequestDTO.getUserEmail();

        userService.register(userRegisterRequestDTO);

        Assertions.assertTrue(userRepository.existsByUserEmail(userEmail));
    }

    @Test
    void withdrawal() {
        UserRegisterRequestDTO userRegisterRequestDTO = new UserRegisterRequestDTO("admin@gmail.com", "admin");
        userService.register(userRegisterRequestDTO);

        String userEmail = userRegisterRequestDTO.getUserEmail();
        Long userId = userService.findIdByUserEmail(userEmail);

        userService.withdrawal(userId);
        Assertions.assertFalse(userRepository.existsById(userId));

        Assertions.assertThrows(
                RefreshTokenNotExistException.class,
                () -> refreshTokenService.findByUserEmail(userEmail)
        );
    }
}