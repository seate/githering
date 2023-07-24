package com.solution.loginSolution.JWT.Service;


import com.solution.loginSolution.JWT.DTO.RefreshTokenRequestDTO;
import com.solution.loginSolution.JWT.Exception.RefreshTokenExpiredException;
import com.solution.loginSolution.JWT.auth.JwtToken;

public interface RefreshTokenService {

    void saveOrUpdate(RefreshTokenRequestDTO refreshTokenRequestDTO);

    void deleteByUserEmail(String userEmail);

    String findByUserEmail(String userEmail);

    JwtToken reIssueTokens(RefreshTokenRequestDTO refreshTokenRequestDTO) throws RefreshTokenExpiredException;
}
