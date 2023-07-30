package com.solution.loginSolution.User.OAuth2.OAuth2UserAttributes;

import com.solution.loginSolution.User.Enums.UserType;
import lombok.Getter;

import java.util.Map;

@Getter
public class NaverOAuth2UserAttributes extends OAuth2UserAttributes {

    public NaverOAuth2UserAttributes(Map<String, Object> attributes) {
        super(attributes);
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        this.userType = UserType.NAVER;
        this.email = (String) response.get("email");
        this.name = (String) response.get("name");
    }
}
