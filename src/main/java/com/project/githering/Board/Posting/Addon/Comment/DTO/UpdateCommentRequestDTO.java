package com.project.githering.Board.Posting.Addon.Comment.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCommentRequestDTO {

    @NotNull
    private Long commentId;

    @NotBlank
    private String content;
}
