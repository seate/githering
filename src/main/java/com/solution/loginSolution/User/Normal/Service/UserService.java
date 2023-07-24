package com.solution.loginSolution.User.Normal.Service;

import com.solution.loginSolution.User.Normal.DTO.UserChangePasswordRequestDTO;
import com.solution.loginSolution.User.Normal.DTO.UserInformResponseDTO;
import com.solution.loginSolution.User.Normal.DTO.UserRegisterRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    //CREATE
    void register(UserRegisterRequestDTO userRegisterRequestDTO);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    //MemberResponseDTO login(MemberRequestDTO memberRequestDTO);

    void logout(String accessToken1);

    void withdrawal(Long userId);

    boolean userEmailDupCheck(String userEmail);


    UserInformResponseDTO getInformation();

    Optional<UserInformResponseDTO> findById(Long id);
    Optional<UserInformResponseDTO> findByUserEmail(String userEmail);

    String findUserEmailById(Long id);

    Long findIdByUserEmail(String userEmail);

    Long findIdByAuthentication();

    Page<UserInformResponseDTO> findAll(Pageable pageable);

    //MemberInfoResponseDTO findMember(MemberRequestDTO memberRequestDTO);
    // UPDATE

    void changePassword(UserChangePasswordRequestDTO userChangePasswordRequestDTO);
}
