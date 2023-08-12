package com.project.githering.Board.Posting.Entity;

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
public class Posting extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long postingId;

    @Column(nullable = false)
    private Long groupId;

    @Column(nullable = false)
    private Long categoryId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Integer viewCount = 0;

    @Column(nullable = false)
    private Integer likeCount = 0;

    @Column(nullable = false)
    private Integer dislikeCount = 0;

    @Column(nullable = false)
    private Boolean isSuperLiked = false;

    @Builder
    public Posting(Long groupId, Long categoryId, String title, Long userId, String content) {
        this.groupId = groupId;
        this.categoryId = categoryId;
        this.title = title;
        this.userId = userId;
        this.content = content;
    }
}