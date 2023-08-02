package com.project.githering.User.OAuth2.OAuth2UserAttributes;

import com.project.githering.User.General.Entity.GeneralUser;
import com.project.githering.User.General.Entity.Role;
import lombok.Getter;

import java.util.Map;

@Getter
public class GithubOAuth2UserAttributes {

    private Map<String, Object> attributes;

    private String loginUser;
    private String name;

    public GithubOAuth2UserAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
        this.loginUser = (String) attributes.get("login");
        this.name = (String) attributes.get("name");
    }

    public GeneralUser toGeneralUser() {
        return toGeneralUser(Role.ROLE_MEMBER);
    }

    public GeneralUser toGeneralUser(Role role) {
        return GeneralUser.builder()
                .loginUser(loginUser)
                .userName(name)
                .role(role)
                .build();
    }
}
