package com.project.githering.Board.BoardCategory.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindAllBoardCategoryRequestDTO {

    @NotNull
    private Long groupId;
}
