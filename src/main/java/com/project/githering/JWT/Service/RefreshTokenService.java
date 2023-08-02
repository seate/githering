package com.project.githering.JWT.Service;


import com.project.githering.JWT.Exception.RefreshTokenExpiredException;
import com.project.githering.JWT.Entity.RefreshToken;
import com.project.githering.JWT.auth.JwtToken;

public interface RefreshTokenService {

    void saveOrUpdate(RefreshToken refreshToken);

    void deleteByLoginUser(String loginUser);

    String findByLoginUser(String loginUser);

    JwtToken reIssueTokens(RefreshToken refreshToken) throws RefreshTokenExpiredException;

    boolean isValid(String refreshTokenValue);
}
