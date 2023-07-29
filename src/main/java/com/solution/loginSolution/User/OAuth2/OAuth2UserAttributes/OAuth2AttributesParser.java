package com.solution.loginSolution.User.OAuth2.OAuth2UserAttributes;

import com.solution.loginSolution.User.OAuth2.Exception.AuthServerNotSupportedException;
import lombok.Getter;

import java.util.Map;


@Getter
public class OAuth2AttributesParser {

    public static OAuth2UserAttributes parse(String registrationId, Map<String, Object> attributes)
            throws AuthServerNotSupportedException {
        return switch (registrationId) {
            case "google" -> new GoogleOAuth2UserAttributes(attributes);
            case "kakao" -> new KakaoOAuth2UserAttributes(attributes);
            case "naver" -> new NaverOAuth2UserAttributes(attributes);
            case "github" -> new GithubOAuth2UserAttributes(attributes);
            default -> throw new AuthServerNotSupportedException();
        };
    }


}
