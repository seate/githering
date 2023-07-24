package com.solution.loginSolution.JWT.Service;


import com.solution.loginSolution.JWT.DTO.LogoutAccessTokenRequestDTO;
import com.solution.loginSolution.JWT.Entity.LogoutAccessToken;

import java.util.Optional;

public interface LogoutAccessTokenService {

    void saveLogoutAccessToken(LogoutAccessTokenRequestDTO logoutAccessTokenRequestDTO);

    Optional<LogoutAccessToken> findByAccessToken(String accessToken);
}
