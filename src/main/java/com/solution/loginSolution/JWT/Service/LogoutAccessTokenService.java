package com.solution.loginSolution.JWT.Service;


import com.solution.loginSolution.JWT.Entity.LogoutAccessToken;

public interface LogoutAccessTokenService {

    void saveLogoutAccessToken(LogoutAccessToken logoutAccessToken);


    boolean existsByAccessToken(String accessToken);

    boolean isValid(String accessToken);
}
