package com.solution.loginSolution.JWT.Service;


import com.solution.loginSolution.JWT.Entity.RefreshToken;
import com.solution.loginSolution.JWT.Exception.RefreshTokenExpiredException;
import com.solution.loginSolution.JWT.auth.JwtToken;

public interface RefreshTokenService {

    void saveOrUpdate(RefreshToken refreshToken);

    void deleteByUserEmail(String userEmail);

    String findByUserEmail(String userEmail);

    JwtToken reIssueTokens(RefreshToken refreshToken) throws RefreshTokenExpiredException;

    boolean isValid(String refreshTokenValue);
}
