package com.solution.loginSolution.JWT.Service;

import com.solution.loginSolution.JWT.DTO.RefreshTokenRequestDTO;
import com.solution.loginSolution.JWT.Entity.RefreshToken;
import com.solution.loginSolution.JWT.Exception.RefreshTokenNotExistException;
import com.solution.loginSolution.JWT.Repository.RefreshTokenRedisRepository;
import com.solution.loginSolution.JWT.auth.JwtToken;
import com.solution.loginSolution.JWT.auth.JwtTokenProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRedisRepository refreshTokenRedisRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public void saveOrUpdate(RefreshTokenRequestDTO refreshTokenRequestDTO) {
        String refreshToken = refreshTokenRequestDTO.getRefreshToken();

        refreshTokenRedisRepository.save(RefreshToken.builder()
                .userEmail(jwtTokenProvider.getUserEmailByRefreshToken(refreshToken))
                .refreshToken(refreshToken)
                .expiration(jwtTokenProvider.getRemainingTimeByRefreshToken(refreshToken)).build());
    }

    @Override
    @Transactional
    public void deleteByUserEmail(String userEmail) {
        refreshTokenRedisRepository.delete(RefreshToken.builder().userEmail(userEmail).build());
    }

    @Override
    public String findByUserEmail(String userEmail) {
        return refreshTokenRedisRepository.findByUserEmail(userEmail)
                .orElseThrow(RefreshTokenNotExistException::new).getRefreshToken();
    }

    @Override
    @Transactional
    public JwtToken reIssueTokens(RefreshTokenRequestDTO refreshTokenRequestDTO) throws RefreshTokenNotExistException {
        String refreshToken = refreshTokenRequestDTO.getRefreshToken();

        jwtTokenProvider.validateRefreshToken(refreshToken);

        Long userId = jwtTokenProvider.getUserIdByRefreshToken(refreshToken);
        String userEmail = jwtTokenProvider.getUserEmailByRefreshToken(refreshToken);
        String newRefreshToken = jwtTokenProvider.createRefreshToken(userId, userEmail);

        refreshTokenRedisRepository.save(
                RefreshToken.builder()
                        .userEmail(userEmail)
                        .refreshToken(newRefreshToken)
                        .expiration(jwtTokenProvider.getRemainingTimeByRefreshToken(newRefreshToken))
                        .build()
        );

        return new JwtToken(jwtTokenProvider.createAccessToken(userId, userEmail), newRefreshToken);
    }
}
