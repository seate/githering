package com.project.githering.User.General.Entity;

import com.project.githering.Common.Entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

@Getter
@Setter
@Entity
@NoArgsConstructor
@SQLDelete(sql = "UPDATE general_user SET deleted = true WHERE id = ?")
@FilterDef(name = "softDeleteGeneralUserFilter",
        parameters = @ParamDef(name = "isDeleted", type = Boolean.class),
        defaultCondition = "deleted = false") //필터를 활성화했을 때 기본적으로 적용
@Filter(name = "softDeleteGeneralUserFilter", condition = "deleted = :isDeleted") //필터 이름을 수정하면 Service의 @DeletedFind도 수정해야함
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

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private boolean deleted = false;

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