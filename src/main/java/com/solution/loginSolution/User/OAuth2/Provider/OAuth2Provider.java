package com.solution.loginSolution.User.OAuth2.Provider;

import com.solution.loginSolution.User.OAuth2.Exception.AuthServerNotSupportedException;
import com.solution.loginSolution.User.OAuth2.OAuth2UserAttributes.GoogleOAuth2UserAttributes;
import com.solution.loginSolution.User.OAuth2.OAuth2UserAttributes.KakaoOAuth2UserAttributes;
import com.solution.loginSolution.User.OAuth2.OAuth2UserAttributes.OAuth2UserAttributes;
import lombok.Getter;

import java.util.Map;


@Getter
public class OAuth2Provider {

    public static OAuth2UserAttributes distinguish(String registrationId, Map<String, Object> attributes)
            throws AuthServerNotSupportedException {
        return switch (registrationId) {
            //TODO kakao, naver
            case "google" -> new GoogleOAuth2UserAttributes(attributes);
            case "kakao" -> new KakaoOAuth2UserAttributes(attributes);
            //case "naver" -> new NaverOauth2UserInfo(attributes);
            default -> throw new AuthServerNotSupportedException();
        };
    }


}
