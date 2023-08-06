package com.project.githering.Board.BoardCategory.DTO;

import com.project.githering.Board.BoardCategory.Entity.BoardCategory;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBoardCategoryRequestDTO {

    @NotBlank
    private Long groupId;

    @NotBlank
    private String categoryName;

    public BoardCategory toEntity() {
        return BoardCategory.builder()
                .groupId(groupId)
                .categoryName(categoryName)
                .build();
    }
}
