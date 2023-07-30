package com.solution.loginSolution.JWT.Service;

import com.solution.loginSolution.JWT.Entity.RefreshToken;
import com.solution.loginSolution.JWT.Exception.RefreshTokenNotExistException;
import com.solution.loginSolution.JWT.Exception.TokenNotValidException;
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
    public void saveOrUpdate(RefreshToken refreshToken) {
        refreshTokenRedisRepository.save(refreshToken);
    }

    @Override
    @Transactional
    public void deleteByUserEmail(String userEmail) {
        refreshTokenRedisRepository.deleteById(userEmail);
    }

    @Override
    public String findByUserEmail(String userEmail) {
        return refreshTokenRedisRepository.findById(userEmail)
                .orElseThrow(RefreshTokenNotExistException::new).getRefreshTokenValue();
    }

    @Override
    @Transactional
    public JwtToken reIssueTokens(RefreshToken refreshToken) throws RefreshTokenNotExistException {
        String refreshTokenValue = refreshToken.getRefreshTokenValue();
        String userEmail = jwtTokenProvider.getUserEmailByRefreshToken(refreshTokenValue);

        jwtTokenProvider.validateRefreshToken(refreshTokenValue);
        if (!isValid(userEmail)) throw new TokenNotValidException();

        Long userId = jwtTokenProvider.getUserIdByRefreshToken(refreshTokenValue);
        String newRefreshToken = jwtTokenProvider.createRefreshToken(userId, userEmail);

        saveOrUpdate(
                RefreshToken.builder()
                        .userEmail(userEmail)
                        .refreshTokenValue(newRefreshToken)
                        .expiration(jwtTokenProvider.getRemainingTimeByRefreshToken(newRefreshToken))
                        .build()
        );

        return new JwtToken(jwtTokenProvider.createAccessToken(userId, userEmail), newRefreshToken);
    }

    @Override
    public boolean isValid(String userEmail) {
        return refreshTokenRedisRepository.existsById(userEmail);
    }
}
