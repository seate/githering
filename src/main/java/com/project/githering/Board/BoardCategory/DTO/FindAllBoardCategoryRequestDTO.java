package com.project.githering.Board.BoardCategory.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindAllBoardCategoryRequestDTO {

    @NotBlank
    private Long groupId;
}
