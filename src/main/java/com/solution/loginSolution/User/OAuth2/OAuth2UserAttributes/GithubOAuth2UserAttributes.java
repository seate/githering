package com.solution.loginSolution.User.OAuth2.OAuth2UserAttributes;

import com.solution.loginSolution.User.Enums.UserType;
import lombok.Getter;

import java.util.Map;

@Getter
public class GithubOAuth2UserAttributes extends OAuth2UserAttributes {

    public GithubOAuth2UserAttributes(Map<String, Object> attributes) {
        super(attributes);
        this.userType = UserType.GITHUB;
        this.email = (String) attributes.get("email");
        this.name = (String) attributes.get("name");
    }
}
