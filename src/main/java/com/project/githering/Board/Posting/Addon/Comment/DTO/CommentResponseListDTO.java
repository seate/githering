package com.project.githering.Board.Posting.Addon.Comment.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CommentResponseListDTO {

    @NotNull
    private Integer commentCount;

    @NotNull
    private List<CommentResponseDTO> commentResponseDTOList;

    public CommentResponseListDTO(List<CommentResponseDTO> commentResponseDTOList) {
        this.commentCount = commentResponseDTOList.size();
        this.commentResponseDTOList = commentResponseDTOList;
    }
}
