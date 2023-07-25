package com.solution.loginSolution.User.OAuth2.GeneralOAuth2User;

import com.solution.loginSolution.User.General.Entity.GeneralUser;
import org.springframework.security.oauth2.core.user.OAuth2User;


public class GeneralOAuth2User extends GeneralUser implements OAuth2User {

    public GeneralOAuth2User(GeneralUser generalUser) {
        super(
                generalUser.getId(),
                generalUser.getUserType(),
                generalUser.getUserName(),
                generalUser.getUserEmail(),
                generalUser.getUserPassword(),
                generalUser.getRole()
        );
    }
}
