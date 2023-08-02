package com.project.githering.JWT.Service;

import com.project.githering.JWT.Entity.LogoutAccessToken;
import com.project.githering.JWT.Repository.LogoutRedisRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutAccessTokenServiceImpl implements LogoutAccessTokenService {

    private final LogoutRedisRepository logoutRedisRepository;

    @Override
    @Transactional
    public void saveLogoutAccessToken(LogoutAccessToken logoutAccessToken) {
        logoutRedisRepository.save(logoutAccessToken);
    }

    @Override
    public boolean existsByAccessToken(String accessToken) {
        return logoutRedisRepository.existsById(accessToken);
    }

    @Override
    public boolean isValid(String accessToken) {
        return !existsByAccessToken(accessToken);
    }
}
