package com.project.githering.Board.Posting.Addon.Comment.DTO;

import com.project.githering.Board.Posting.Addon.Comment.Entity.Comment;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CommentResponseDTO {

    @NotNull
    private Long commentId;

    @NotNull
    private Long userId;

    @NotNull
    private String userName;

    @NotNull
    private Long parentCommentId;

    @NotNull
    private String content;

    private LocalDateTime createTime;

    @NotNull
    private Boolean isDeleted;

    public CommentResponseDTO(String userName, Comment comment) {
        this.commentId = comment.getCommentId();
        this.userId = comment.getUserId();
        this.userName = userName;
        this.parentCommentId = comment.getParentCommentId();
        this.content = (comment.getIsDeleted()) ? "삭제된 댓글입니다." : comment.getContent();
        this.createTime = comment.getCreateTime();
        this.isDeleted = comment.getIsDeleted();
    }
}
