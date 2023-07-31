package com.solution.loginSolution.User.OAuth2.OAuth2UserAttributes;

import com.solution.loginSolution.User.General.Enum.UserType;
import lombok.Getter;

import java.util.Map;


@Getter
public class GoogleOAuth2UserAttributes extends OAuth2UserAttributes {

    public GoogleOAuth2UserAttributes(Map<String, Object> attributes) {
        super(attributes);
        this.userType = UserType.GOOGLE;
        this.email = (String) attributes.get("email");
        this.name = (String) attributes.get("name");
    }
}
