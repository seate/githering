package com.project.githering.JWT.Service;

import com.project.githering.JWT.Entity.RefreshToken;
import com.project.githering.JWT.Exception.RefreshTokenNotExistException;
import com.project.githering.JWT.Exception.TokenNotValidException;
import com.project.githering.JWT.Repository.RefreshTokenRedisRepository;
import com.project.githering.JWT.auth.JwtToken;
import com.project.githering.JWT.auth.JwtTokenProvider;
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
    public void deleteByLoginUser(String loginUser) {
        refreshTokenRedisRepository.deleteById(loginUser);
    }

    @Override
    public String findByLoginUser(String loginUser) throws RefreshTokenNotExistException {
        return refreshTokenRedisRepository.findById(loginUser)
                .orElseThrow(RefreshTokenNotExistException::new).getRefreshTokenValue();
    }

    @Override
    @Transactional
    public JwtToken reIssueTokens(RefreshToken refreshToken) throws RefreshTokenNotExistException, TokenNotValidException {
        String refreshTokenValue = refreshToken.getRefreshTokenValue();
        String loginUser = jwtTokenProvider.getLoginUserByRefreshToken(refreshTokenValue);

        jwtTokenProvider.validateRefreshToken(refreshTokenValue);
        if (!isValid(loginUser)) throw new TokenNotValidException();

        Long userId = jwtTokenProvider.getUserIdByRefreshToken(refreshTokenValue);
        String newRefreshToken = jwtTokenProvider.createRefreshToken(userId, loginUser);

        saveOrUpdate(
                RefreshToken.builder()
                        .loginUser(loginUser)
                        .refreshTokenValue(newRefreshToken)
                        .expiration(jwtTokenProvider.getRemainingTimeByRefreshToken(newRefreshToken))
                        .build()
        );

        return new JwtToken(jwtTokenProvider.createAccessToken(userId, loginUser), newRefreshToken);
    }

    @Override
    public boolean isValid(String loginUser) {
        return refreshTokenRedisRepository.existsById(loginUser);
    }
}
