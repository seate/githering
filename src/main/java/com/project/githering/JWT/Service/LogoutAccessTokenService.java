package com.project.githering.JWT.Service;


import com.project.githering.JWT.Entity.LogoutAccessToken;

public interface LogoutAccessTokenService {

    void saveLogoutAccessToken(LogoutAccessToken logoutAccessToken);


    boolean existsByAccessToken(String accessToken);

    boolean isValid(String accessToken);
}
