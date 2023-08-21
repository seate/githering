package com.project.githering.Board.Posting.Addon.Comment.DTO;

import com.project.githering.Board.Posting.Addon.Comment.Entity.Comment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentRequestDTO {

    @NotNull
    private Long postingId;

    private Long parentCommentId;

    @NotBlank
    private String content;



    public Comment toEntity(Long userId) {
        return Comment.builder()
                .postingId(postingId)
                .parentCommentId(parentCommentId)
                .userId(userId)
                .content(content)
                .build();
    }
}
