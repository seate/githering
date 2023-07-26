package com.solution.loginSolution.JWT.Service;

import com.solution.loginSolution.JWT.DTO.LogoutAccessTokenRequestDTO;
import com.solution.loginSolution.JWT.Entity.LogoutAccessToken;
import com.solution.loginSolution.JWT.Repository.LogoutRedisRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LogoutAccessTokenServiceImpl implements LogoutAccessTokenService {

    private final LogoutRedisRepository logoutRedisRepository;

    @Override
    @Transactional
    public void saveLogoutAccessToken(LogoutAccessTokenRequestDTO logoutAccessTokenRequestDTO) {
        logoutRedisRepository.save(logoutAccessTokenRequestDTO.toEntity());
    }

    @Override
    public Optional<LogoutAccessToken> findByAccessToken(String accessToken) {
        return logoutRedisRepository.findByAccessToken(accessToken);
    }
}
