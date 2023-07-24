package com.solution.loginSolution.User.Normal.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PrincipalDetails extends NormalUser implements UserDetails {

    private NormalUser normalUser;
    private Map<String, Object> userAttributes;

    public PrincipalDetails(NormalUser normalUser) {
        this.normalUser = normalUser;
        this.userAttributes = new HashMap<>();
    }

    public NormalUser getMember() {
        return normalUser;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(normalUser.getRole().name()));

        return grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return normalUser.getUserEmail();
    }

    public String getPassword() {
        return normalUser.getUserPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
