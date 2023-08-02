package com.project.githering.User.OAuth2.GeneralOAuth2User;

import com.project.githering.User.General.Entity.GeneralUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;


public class GeneralOAuth2User extends GeneralUser implements OAuth2User {

    public GeneralOAuth2User(GeneralUser generalUser) {
        super(
                generalUser.getId(),
                generalUser.getLoginUser(),
                generalUser.getUserName(),
                generalUser.getRole()
        );
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(getRole().name()));

        return grantedAuthorities;
    }

    @Override
    public String getName() {
        return getUserName();
    }
}
