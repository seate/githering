package com.solution.loginSolution.User.General.Service;

import com.solution.loginSolution.JWT.DTO.LogoutAccessTokenRequestDTO;
import com.solution.loginSolution.JWT.Service.LogoutAccessTokenService;
import com.solution.loginSolution.JWT.Service.RefreshTokenService;
import com.solution.loginSolution.JWT.auth.JwtTokenProvider;
import com.solution.loginSolution.User.General.DTO.UserChangePasswordRequestDTO;
import com.solution.loginSolution.User.General.DTO.UserInformResponseDTO;
import com.solution.loginSolution.User.General.DTO.UserRegisterRequestDTO;
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
@Transactional
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
    public void register(UserRegisterRequestDTO userRegisterRequestDTO) throws UserExistException {
        if (generalUserRepository.existsByUserEmail(userRegisterRequestDTO.getUserEmail())) {
            log.warn("UserExistException in register");
            throw new UserExistException();
        }

        GeneralUser generalUser = userRegisterRequestDTO
                .toEntity(
                        passwordEncoder
                                .encode(userRegisterRequestDTO.getPassword())
                );

        generalUserRepository.save(generalUser);
    }

    // DELETE
    @Override
    public void withdrawal(Long userId, String accessToken) {
        logout(accessToken);
        GeneralUser generalUser = generalUserRepository.findById(userId).orElseThrow(UserNotExistException::new);
        generalUserRepository.delete(generalUser);
        // 소셜 로그인일 경우 auth server에서 받은 토큰을 삭제하는 것은 생략
        // 1시간 정도로 자동 무효화되기 때문
    }

    // READ

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        GeneralUser generalUser = generalUserRepository.findByUserEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new PrincipalDetails(generalUser);
    }

    @Override
    public void logout(String accessToken) {
        Long remainingTime = jwtTokenProvider.getRemainingTimeByAccessToken(accessToken);
        logoutAccessTokenService.saveLogoutAccessToken(new LogoutAccessTokenRequestDTO(accessToken, remainingTime));
        refreshTokenService.deleteByUserEmail(jwtTokenProvider.getUserEmailByAccessToken(accessToken));
    }

    @Override
    public boolean userEmailDupCheck(String userEmail) {
        return generalUserRepository.existsByUserEmail(userEmail);
    }

    @Override
    public UserInformResponseDTO getInformation() {
        Long userId = findIdByAuthentication();
        GeneralUser generalUser = generalUserRepository.findById(userId).orElseThrow(UserNotExistException::new);
        return new UserInformResponseDTO(generalUser);
    }

    @Override
    public Optional<UserInformResponseDTO> findById(Long id) {
        return generalUserRepository.findById(id).map(UserInformResponseDTO::new);
    }

    @Override
    public Optional<UserInformResponseDTO> findByUserEmail(String userEmail) {
        return generalUserRepository.findByUserEmail(userEmail).map(UserInformResponseDTO::new);
    }

    @Override
    public String findUserEmailById(Long id) {
        return generalUserRepository.findById(id).orElseThrow(UserNotExistException::new).getUserEmail();
    }

    @Override
    public Long findIdByUserEmail(String userEmail) {
        return generalUserRepository.findByUserEmail(userEmail).orElseThrow(UserNotExistException::new).getId();
    }

    @Override
    public Long findIdByAuthentication() {
        String userEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return findIdByUserEmail(userEmail);
    }

    @Override
    public Page<UserInformResponseDTO> findAll(Pageable pageable) {
        return generalUserRepository.findAll(pageable).map(UserInformResponseDTO::new);
    }

    // UPDATE
    @Override
    public void changePassword(UserChangePasswordRequestDTO userChangePasswordRequestDTO) {
        Long userId = findIdByAuthentication();
        GeneralUser generalUser = generalUserRepository.findById(userId).orElseThrow(UserNotExistException::new);
        generalUser.setUserPassword(passwordEncoder.encode(userChangePasswordRequestDTO.getNewPassword()));
        generalUserRepository.save(generalUser);
    }
}
