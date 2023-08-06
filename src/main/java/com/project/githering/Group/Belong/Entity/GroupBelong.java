package com.project.githering.Group.Belong.Entity;

import com.project.githering.Common.Entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@IdClass(GroupBelongPk.class)
public class GroupBelong extends BaseTimeEntity {

    @Id
    @Column(nullable = false)
    private Long groupId;

    @Id
    @Column(nullable = false)
    private Long userId;

    public GroupBelong(Long groupId, Long userId) {
        this.groupId = groupId;
        this.userId = userId;
    }
}
