package com.solution.loginSolution.JWT.Service;

import com.solution.loginSolution.JWT.DTO.RefreshTokenRequestDTO;
import com.solution.loginSolution.JWT.auth.JwtTokenProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RefreshTokenServiceImplTest {

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Test
    void 잘_찾니() {
        String refreshToken = jwtTokenProvider.createRefreshToken(3L, "userssss");
        refreshTokenService.saveOrUpdate(new RefreshTokenRequestDTO(refreshToken).toEntity(jwtTokenProvider));

        String userEmail = jwtTokenProvider.getUserEmailByRefreshToken(refreshToken);

        String findRefreshToken = refreshTokenService.findByUserEmail(userEmail);

        Assertions.assertEquals(refreshToken, findRefreshToken);
    }
}