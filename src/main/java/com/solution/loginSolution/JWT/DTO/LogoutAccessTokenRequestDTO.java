package com.solution.loginSolution.JWT.DTO;

import com.solution.loginSolution.JWT.Entity.LogoutAccessToken;
import com.solution.loginSolution.JWT.auth.JwtTokenProvider;
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
