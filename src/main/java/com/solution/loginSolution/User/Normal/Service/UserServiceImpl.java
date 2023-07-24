package com.solution.loginSolution.User.Normal.Service;

import com.solution.loginSolution.JWT.DTO.LogoutAccessTokenRequestDTO;
import com.solution.loginSolution.JWT.Service.LogoutAccessTokenService;
import com.solution.loginSolution.JWT.Service.RefreshTokenService;
import com.solution.loginSolution.JWT.auth.JwtTokenProvider;
import com.solution.loginSolution.User.Normal.DTO.UserChangePasswordRequestDTO;
import com.solution.loginSolution.User.Normal.DTO.UserInformResponseDTO;
import com.solution.loginSolution.User.Normal.DTO.UserRegisterRequestDTO;
import com.solution.loginSolution.User.Normal.Entity.NormalUser;
import com.solution.loginSolution.User.Normal.Entity.PrincipalDetails;
import com.solution.loginSolution.User.Normal.Exception.UserExistException;
import com.solution.loginSolution.User.Normal.Exception.UserNotExistException;
import com.solution.loginSolution.User.Normal.Repository.UserRepository;
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
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

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
        if (userRepository.existsByUserEmail(userRegisterRequestDTO.getUserEmail())) {
            log.warn("UserExistException in register");
            throw new UserExistException();
        }

        NormalUser normalUser = userRegisterRequestDTO
                .toEntity(
                        passwordEncoder
                                .encode(userRegisterRequestDTO.getPassword())
                );

        userRepository.save(normalUser);
    }

    // DELETE
    @Override
    public void withdrawal(Long userId) {
        NormalUser normalUser = userRepository.findById(userId).orElseThrow(UserNotExistException::new);
        userRepository.delete(normalUser);
    }

    // READ

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        NormalUser normalUser = userRepository.findByUserEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new PrincipalDetails(normalUser);
    }

    @Override
    public void logout(String accessToken) {
        Long remainingTime = jwtTokenProvider.getRemainingTimeByAccessToken(accessToken);
        logoutAccessTokenService.saveLogoutAccessToken(new LogoutAccessTokenRequestDTO(accessToken, remainingTime));
        refreshTokenService.deleteByUserEmail(jwtTokenProvider.getUserEmailByAccessToken(accessToken));
    }

    @Override
    public boolean userEmailDupCheck(String userEmail) {
        return userRepository.existsByUserEmail(userEmail);
    }

    @Override
    public UserInformResponseDTO getInformation() {
        Long userId = findIdByAuthentication();
        NormalUser normalUser = userRepository.findById(userId).orElseThrow(UserNotExistException::new);
        return new UserInformResponseDTO(normalUser);
    }

    @Override
    public Optional<UserInformResponseDTO> findById(Long id) {
        return userRepository.findById(id).map(UserInformResponseDTO::new);
    }

    @Override
    public Optional<UserInformResponseDTO> findByUserEmail(String userEmail) {
        return userRepository.findByUserEmail(userEmail).map(UserInformResponseDTO::new);
    }

    @Override
    public String findUserEmailById(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotExistException::new).getUserEmail();
    }

    @Override
    public Long findIdByUserEmail(String userEmail) {
        return userRepository.findByUserEmail(userEmail).orElseThrow(UserNotExistException::new).getId();
    }

    @Override
    public Long findIdByAuthentication() {
        String userEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return findIdByUserEmail(userEmail);
    }

    @Override
    public Page<UserInformResponseDTO> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserInformResponseDTO::new);
    }

    // UPDATE
    @Override
    public void changePassword(UserChangePasswordRequestDTO userChangePasswordRequestDTO) {
        Long userId = findIdByAuthentication();
        NormalUser normalUser = userRepository.findById(userId).orElseThrow(UserNotExistException::new);
        normalUser.setUserPassword(passwordEncoder.encode(userChangePasswordRequestDTO.getNewPassword()));
        userRepository.save(normalUser);
    }
}
