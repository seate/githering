package com.solution.loginSolution.User.General.Entity;

import com.solution.loginSolution.Common.Entity.BaseTimeEntity;
import com.solution.loginSolution.User.Common.enums.UserType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class GeneralUser extends BaseTimeEntity implements OAuth2User {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false, unique = true)
    private String userEmail;

    private String userPassword;

    //@Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;


    @Builder
    public GeneralUser(Long id, UserType userType, String userName, String userEmail, String userPassword, Role role) {
        this.id = id;
        this.userType = userType;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.role = role;
    }

    public GeneralUser update(String userName) {
        this.userName = userName;
        return this;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getName() {
        return this.userEmail;
    }
}