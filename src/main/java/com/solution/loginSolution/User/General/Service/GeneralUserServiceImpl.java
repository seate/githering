package com.solution.loginSolution.User.General.Service;

import com.solution.loginSolution.JWT.DTO.LogoutAccessTokenRequestDTO;
import com.solution.loginSolution.JWT.Service.LogoutAccessTokenService;
import com.solution.loginSolution.JWT.Service.RefreshTokenService;
import com.solution.loginSolution.JWT.auth.JwtTokenProvider;
import com.solution.loginSolution.User.General.Entity.GeneralUser;
import com.solution.loginSolution.User.General.Entity.PrincipalDetails;
import com.solution.loginSolution.User.General.Exception.UserExistException;
import com.solution.loginSolution.User.General.Exception.UserNotExistException;
import com.solution.loginSolution.User.General.Repository.GeneralUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GeneralUserServiceImpl implements GeneralUserService {

    private final GeneralUserRepository generalUserRepository;

    private PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    private final RefreshTokenService refreshTokenService;

    private final LogoutAccessTokenService logoutAccessTokenService;

    @Bean //Bean으로 등록되어 있어야함
    public PasswordEncoder passwordEncoder() {
        this.passwordEncoder = new BCryptPasswordEncoder();
        return this.passwordEncoder;
    }

    // CREATE
    @Override
    @Transactional
    public void register(GeneralUser generalUser) throws UserExistException {
        if (generalUserRepository.existsByUserEmail(generalUser.getUserEmail())) {
            log.warn("UserExistException in register");
            throw new UserExistException();
        }

        generalUser.setUserPassword(passwordEncoder.encode(generalUser.getUserPassword()));
        generalUserRepository.save(generalUser);
    }

    @Override
    @Transactional
    public GeneralUser saveOrUpdate(GeneralUser generalUser) {
        return generalUserRepository.findByUserEmail(generalUser.getUserEmail())
                .map(entity -> entity.update(generalUser.getUserName()))
                .orElseGet(() -> generalUserRepository.save(generalUser));
    }

    // DELETE
    @Override
    @Transactional
    public void withdrawal(Long userId, String accessToken) throws UserNotExistException {
        logout(accessToken);
        GeneralUser generalUser = generalUserRepository.findById(userId).orElseThrow(UserNotExistException::new);
        generalUserRepository.delete(generalUser);
        // 소셜 로그인일 경우 auth server에서 받은 토큰을 삭제하는 것은 생략
        // 1시간 정도로 자동 무효화되기 때문
    }

    // READ

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        GeneralUser generalUser = generalUserRepository.findByUserEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new PrincipalDetails(generalUser);
    }

    @Override
    @Transactional
    public void logout(String accessToken) {
        logoutAccessTokenService.saveLogoutAccessToken(new LogoutAccessTokenRequestDTO(accessToken).toEntity(jwtTokenProvider));
        refreshTokenService.deleteByUserEmail(jwtTokenProvider.getUserEmailByAccessToken(accessToken));
    }

    @Override
    public boolean userEmailDupCheck(String userEmail) {
        return generalUserRepository.existsByUserEmail(userEmail);
    }

    @Override
    public Optional<GeneralUser> findById(Long id) {
        return generalUserRepository.findById(id);
    }

    @Override
    public Optional<GeneralUser> findByUserEmail(String userEmail) {
        return generalUserRepository.findByUserEmail(userEmail);
    }

    @Override
    public String findUserEmailById(Long id) throws UserNotExistException {
        return generalUserRepository.findById(id).orElseThrow(UserNotExistException::new).getUserEmail();
    }

    @Override
    public Long findIdByUserEmail(String userEmail) throws UserNotExistException {
        return generalUserRepository.findByUserEmail(userEmail).orElseThrow(UserNotExistException::new).getId();
    }

    @Override
    public Long findIdByAuthentication() {
        GeneralUser generalUser = (GeneralUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return generalUser.getId();
    }

    @Override
    public Page<GeneralUser> findAll(Pageable pageable) {
        return generalUserRepository.findAll(pageable);
    }

    // UPDATE
    @Override
    @Transactional
    public void changePassword(Long userId, String newPassword) throws UserNotExistException {
        GeneralUser generalUser = generalUserRepository.findById(userId).orElseThrow(UserNotExistException::new);
        generalUser.setUserPassword(passwordEncoder.encode(newPassword));
        generalUserRepository.save(generalUser);
    }
}
