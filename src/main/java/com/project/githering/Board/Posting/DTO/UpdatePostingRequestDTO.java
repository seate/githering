package com.project.githering.Board.Posting.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePostingRequestDTO {

    @NotNull
    private Long categoryId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}