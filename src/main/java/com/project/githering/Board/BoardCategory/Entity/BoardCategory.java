package com.project.githering.Board.BoardCategory.Entity;

import com.project.githering.Common.Entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(indexes = @Index(name = "idx_groupId", columnList = "groupId"))
public class BoardCategory extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long categoryId;

    @Column(nullable = false)
    private Long groupId;

    @Column
    private String categoryName;

    @Builder
    public BoardCategory(Long groupId, String categoryName) {
        this.groupId = groupId;
        this.categoryName = categoryName;
    }
}