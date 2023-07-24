package com.solution.loginSolution.JWT.DTO;

import com.solution.loginSolution.JWT.Entity.LogoutAccessToken;
import lombok.Data;

@Data
public class LogoutAccessTokenRequestDTO {

    private String accessToken;

    private Long remainingTime;

    public LogoutAccessTokenRequestDTO(String accessToken, Long remainingTime) {
        this.accessToken = accessToken;
        this.remainingTime = remainingTime;
    }

    public LogoutAccessToken toEntity() {
        return LogoutAccessToken.builder()
                .accessToken(accessToken)
                .expiration(remainingTime)
                .build();
    }
}
