package com.project.githering.User.General.Entity;

import com.project.githering.Common.Entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "general_user", indexes = {@Index(name = "id_index", columnList = "id")})
public class GeneralUser extends BaseTimeEntity {

    @Id
    @Column(nullable = false, name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String loginUser;

    @Column(nullable = false)
    private String userName;

    //@Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;


    @Builder
    public GeneralUser(Long id, String loginUser, String userName, Role role) {
        this.id = id;
        this.loginUser = loginUser;
        this.userName = userName;
        this.role = role;
    }

    public GeneralUser update(String userName) {
        this.userName = userName;
        return this;
    }
}