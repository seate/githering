package com.solution.loginSolution.JWT.DTO;

import com.solution.loginSolution.JWT.Entity.RefreshToken;
import com.solution.loginSolution.JWT.auth.JwtTokenProvider;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RefreshTokenRequestDTO {
    @NotBlank
    private String refreshToken;

    public RefreshToken toEntity(JwtTokenProvider jwtTokenProvider) {
        return RefreshToken.builder()
                .userEmail(jwtTokenProvider.getUserEmailByRefreshToken(refreshToken))
                .refreshTokenValue(refreshToken)
                .expiration(jwtTokenProvider.getRemainingTimeByRefreshToken(refreshToken)).build();
    }
}
