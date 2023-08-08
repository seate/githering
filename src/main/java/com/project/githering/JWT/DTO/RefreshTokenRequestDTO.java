package com.project.githering.JWT.DTO;

import com.project.githering.JWT.Entity.RefreshToken;
import com.project.githering.JWT.auth.JwtTokenProvider;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenRequestDTO {
    @NotBlank
    private String refreshToken;

    public RefreshToken toEntity(JwtTokenProvider jwtTokenProvider) {
        return RefreshToken.builder()
                .loginUser(jwtTokenProvider.getLoginUserByRefreshToken(refreshToken))
                .refreshTokenValue(refreshToken)
                .expiration(jwtTokenProvider.getRemainingTimeByRefreshToken(refreshToken)).build();
    }
}
