package com.project.githering.JWT.Service;

import com.project.githering.JWT.DTO.RefreshTokenRequestDTO;
import com.project.githering.JWT.auth.JwtTokenProvider;
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

        String loginUser = jwtTokenProvider.getLoginUserByRefreshToken(refreshToken);

        String findRefreshToken = refreshTokenService.findByLoginUser(loginUser);

        Assertions.assertEquals(refreshToken, findRefreshToken);
    }
}