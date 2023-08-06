package com.project.githering.Group.Entity;

import com.project.githering.Common.Entity.BaseTimeEntity;
import com.project.githering.Group.Enum.GroupType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "groups")
public class Group extends BaseTimeEntity {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;

    @Column
    private Long groupMasterId;

    @Column(nullable = false)
    private GroupType groupType;

    @Column(nullable = false, unique = true)
    private String groupName;

    @Column
    private String groupDescription;

    @Builder
    public Group(Long groupMasterId, GroupType groupType, String groupName, String groupDescription) {
        this.groupMasterId = groupMasterId;
        this.groupType = groupType;
        this.groupName = groupName;
        this.groupDescription = groupDescription;
    }
}
