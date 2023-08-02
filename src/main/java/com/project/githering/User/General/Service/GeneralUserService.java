package com.project.githering.User.General.Service;

import com.project.githering.User.General.Entity.GeneralUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Optional;

// jwtAuthenticationFilter에서 authenticate하기 위해 userDetailsService를 상속받아야함
public interface GeneralUserService extends OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    void logout(String accessToken);

    void withdrawal(Long userId, String accessToken);

    Optional<GeneralUser> findById(Long id);

    Optional<GeneralUser> findByLoginUser(String loginUser);

    String findLoginUserById(Long id);

    Long findIdByLoginUser(String loginUser);

    Long findIdByAuthentication();

    Page<GeneralUser> findAll(Pageable pageable);
}
