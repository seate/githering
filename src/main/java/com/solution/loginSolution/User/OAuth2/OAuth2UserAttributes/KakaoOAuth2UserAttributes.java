package com.solution.loginSolution.User.OAuth2.OAuth2UserAttributes;

import com.solution.loginSolution.User.General.Enum.UserType;
import lombok.Getter;

import java.util.Map;

@Getter
public class KakaoOAuth2UserAttributes extends OAuth2UserAttributes {

    public KakaoOAuth2UserAttributes(Map<String, Object> attributes) {
        super(attributes);
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> kakaoProfile = (Map<String, Object>) kakaoAccount.get("profile");

        this.userType = UserType.KAKAO;
        this.email = (String) kakaoAccount.get("email");
        this.name = (String) kakaoProfile.get("nickname");
    }
}
