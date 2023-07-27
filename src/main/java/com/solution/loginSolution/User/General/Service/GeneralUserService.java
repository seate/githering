package com.solution.loginSolution.User.General.Service;

import com.solution.loginSolution.User.General.Entity.GeneralUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

// jwtAuthenticationFilter에서 authenticate하기 위해 userDetailsService를 상속받아야함
public interface GeneralUserService extends UserDetailsService {

    //CREATE
    void register(GeneralUser generalUser);

    GeneralUser saveOrUpdate(GeneralUser generalUser);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    //MemberResponseDTO login(MemberRequestDTO memberRequestDTO);

    void logout(String accessToken);

    void withdrawal(Long userId, String accessToken);

    boolean userEmailDupCheck(String userEmail);



    Optional<GeneralUser> findById(Long id);

    Optional<GeneralUser> findByUserEmail(String userEmail);

    String findUserEmailById(Long id);

    Long findIdByUserEmail(String userEmail);

    Long findIdByAuthentication();

    Page<GeneralUser> findAll(Pageable pageable);
    //MemberInfoResponseDTO findMember(MemberRequestDTO memberRequestDTO);

    // UPDATE

    void changePassword(Long userId, String newPassword);
}
