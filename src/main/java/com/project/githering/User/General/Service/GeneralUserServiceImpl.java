package com.project.githering.User.General.Service;

import com.project.githering.JWT.DTO.LogoutAccessTokenRequestDTO;
import com.project.githering.JWT.Service.LogoutAccessTokenService;
import com.project.githering.JWT.Service.RefreshTokenService;
import com.project.githering.JWT.auth.JwtTokenProvider;
import com.project.githering.User.General.Entity.GeneralUser;
import com.project.githering.User.General.Entity.Role;
import com.project.githering.User.General.Exception.UserNotExistException;
import com.project.githering.User.General.Repository.GeneralUserRepository;
import com.project.githering.User.OAuth2.GeneralOAuth2User.GeneralOAuth2User;
import com.project.githering.User.OAuth2.OAuth2UserAttributes.GithubOAuth2UserAttributes;
import com.project.githering.User.OAuth2.OAuth2UserAttributes.OAuth2AttributesDispatcher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GeneralUserServiceImpl implements GeneralUserService {

    private final GeneralUserRepository generalUserRepository;

    private final OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();

    private PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    private final RefreshTokenService refreshTokenService;

    private final LogoutAccessTokenService logoutAccessTokenService;

    @Bean //Bean으로 등록되어 있어야함
    public PasswordEncoder passwordEncoder() {
        this.passwordEncoder = new BCryptPasswordEncoder();
        return this.passwordEncoder;
    }

    @Override // oAuth2 client가 기타 처리를 한 후 OAuth2UserRequest를 건내줌
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // OAuth2 서비스 id 구분코드 (구글, 네이버, 카카오 등)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);
        GithubOAuth2UserAttributes userAttributes = OAuth2AttributesDispatcher.dispatch(registrationId, oAuth2User.getAttributes());

        GeneralUser generalUser;
        Optional<GeneralUser> byLoginUser = generalUserRepository.findByLoginUser(userAttributes.getLoginUser());
        if (byLoginUser.isPresent()) generalUser = userAttributes.toGeneralUser(byLoginUser.get().getRole());
        else generalUser = userAttributes.toGeneralUser();

        // saveOrUpdate
        GeneralUser findGeneralUser = generalUserRepository.findByLoginUser(generalUser.getLoginUser())
                .map(entity -> entity.update(generalUser.getUserName(), true/*soft delete의 재생성을 위해*/))
                .orElseGet(() -> generalUserRepository.save(generalUser));

        // stateful일 경우 securityContextHolder에 저장되지만 stateless일 경우 저장되지 않음
        // repository에 저장은 saveOrUpdate에서 하고, 성공할 경우 successHandler에서 jwt 처리를 함
        return new GeneralOAuth2User(findGeneralUser);
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

    @Override
    @Transactional
    public void logout(String accessToken) {
        logoutAccessTokenService.saveLogoutAccessToken(new LogoutAccessTokenRequestDTO(accessToken).toEntity(jwtTokenProvider));
        refreshTokenService.deleteByLoginUser(jwtTokenProvider.getLoginUserByAccessToken(accessToken));
    }

    @Override
    public Optional<GeneralUser> findById(Long id) {
        return generalUserRepository.findById(id);
    }

    @Override
    public Optional<GeneralUser> findByLoginUser(String loginUser) {
        return generalUserRepository.findByLoginUser(loginUser);
    }

    @Override
    public String findLoginUserById(Long id) throws UserNotExistException {
        return generalUserRepository.findById(id).orElseThrow(UserNotExistException::new).getLoginUser();
    }

    @Override
    public Long findIdByLoginUser(String loginUser) throws UserNotExistException {
        return generalUserRepository.findByLoginUser(loginUser).orElseThrow(UserNotExistException::new).getId();
    }

    @Override
    public Long findIdByAuthentication() {
        GeneralUser generalUser = (GeneralUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return generalUser.getId();
    }

    @Override
    public List<GeneralUser> findAllAdmin() {
        return generalUserRepository.findAllByRole(Role.ROLE_ADMIN);
    }

    @Override
    public Page<GeneralUser> findAll(Pageable pageable) {
        return generalUserRepository.findAll(pageable);
    }
}
