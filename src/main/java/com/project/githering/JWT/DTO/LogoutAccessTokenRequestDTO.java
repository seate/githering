package com.project.githering.JWT.DTO;

import com.project.githering.JWT.Entity.LogoutAccessToken;
import com.project.githering.JWT.auth.JwtTokenProvider;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LogoutAccessTokenRequestDTO {

    private String accessToken;

    public LogoutAccessToken toEntity(JwtTokenProvider jwtTokenProvider) {
        return LogoutAccessToken.builder()
                .accessTokenValue(accessToken)
                .expiration(jwtTokenProvider.getRemainingTimeByAccessToken(accessToken))
                .build();
    }
}
