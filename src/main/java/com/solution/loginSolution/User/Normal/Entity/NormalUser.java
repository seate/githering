package com.solution.loginSolution.User.Normal.Entity;

import com.solution.loginSolution.Common.Entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class NormalUser extends BaseTimeEntity {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String userPassword;

    //@Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;


    @Builder
    public NormalUser(String userEmail, String userPassword, Role role) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.role = role;
    }
}