package com.project.githering.Board.Posting.Addon.Comment.Entity;

import com.project.githering.Common.Entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

@Setter
@Getter
@Entity
@NoArgsConstructor
@SQLDelete(sql = "UPDATE comment SET isDeleted = true WHERE id = ?")
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long commentId;

    @Column(nullable = false)
    private Long postingId;

    @Column(nullable = false)
    private Long userId;

    @Column
    private Long parentCommentId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Boolean isDeleted;

    @Builder
    public Comment(Long postingId, Long userId, Long parentCommentId, String content) {
        this.postingId = postingId;
        this.userId = userId;
        this.parentCommentId = parentCommentId;
        this.content = content;
        this.isDeleted = false;
    }
}
