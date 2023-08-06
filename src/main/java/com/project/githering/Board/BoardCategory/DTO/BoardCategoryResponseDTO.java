package com.project.githering.Board.BoardCategory.DTO;

import com.project.githering.Board.BoardCategory.Entity.BoardCategory;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardCategoryResponseDTO {

    @NotBlank
    private Long categoryId;

    @NotBlank
    private String categoryName;

    public BoardCategoryResponseDTO(BoardCategory boardCategory) {
        this.categoryId = boardCategory.getCategoryId();
        this.categoryName = boardCategory.getCategoryName();
    }
}
