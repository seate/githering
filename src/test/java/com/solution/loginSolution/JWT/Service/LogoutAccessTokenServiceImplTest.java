package com.solution.loginSolution.JWT.Service;

import com.solution.loginSolution.JWT.DTO.LogoutAccessTokenRequestDTO;
import com.solution.loginSolution.JWT.Repository.LogoutRedisRepository;
import com.solution.loginSolution.JWT.auth.JwtTokenProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LogoutAccessTokenServiceImplTest {

    @Autowired
    private LogoutAccessTokenService logoutAccessTokenService;

    @Autowired
    private LogoutRedisRepository logoutRedisRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @BeforeEach
    void setUp() {
        logoutRedisRepository.deleteAll();
    }


    @Test
    void 잘_찾니() {
        String accessToken = jwtTokenProvider.createAccessToken(30L, "userssss");

        logoutAccessTokenService
                .saveLogoutAccessToken(new LogoutAccessTokenRequestDTO(accessToken).toEntity(jwtTokenProvider));

        Assertions.assertTrue(logoutAccessTokenService.existsByAccessToken(accessToken));
    }
}