package com.solution.loginSolution.User.OAuth2.Service;

import com.solution.loginSolution.User.General.Entity.GeneralUser;
import com.solution.loginSolution.User.General.Service.GeneralUserService;
import com.solution.loginSolution.User.OAuth2.GeneralOAuth2User.GeneralOAuth2User;
import com.solution.loginSolution.User.OAuth2.OAuth2UserAttributes.OAuth2AttributesDispatcher;
import com.solution.loginSolution.User.OAuth2.OAuth2UserAttributes.OAuth2UserAttributes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final GeneralUserService generalUserService;
    private final OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();

    /*@Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService() {
        return
    }*/

    @Override // oAuth2 client가 기타 처리를 한 후 OAuth2UserRequest를 건내줌
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

        // stateful일 경우 securityContextHolder에 저장되지만 stateless일 경우 저장되지 않음
        // repository에 저장은 이곳에서 하고, 성공할 경우 successHandler에서 jwt 처리를 함
        return processOAuth2User(userRequest, oAuth2User);
    }

    protected OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        // OAuth2 서비스 id 구분코드 (구글, 네이버, 카카오 등)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        OAuth2UserAttributes userAttributes = OAuth2AttributesDispatcher.dispatch(registrationId, oAuth2User.getAttributes());

        GeneralUser generalUser = generalUserService.saveOrUpdate(userAttributes.toGeneralUser());

        return new GeneralOAuth2User(generalUser);
    }
}
