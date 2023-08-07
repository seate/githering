package com.project.githering.Board.Posting.DTO;

import com.project.githering.Board.Posting.Entity.Posting;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SimplePostingInformResponseDTO {

    @NotBlank
    private String categoryName;

    @NotBlank
    private String title;

    @NotBlank
    private String userName;

    @NotNull
    private Integer viewCount;

    public SimplePostingInformResponseDTO(String categoryName, String userName, Posting posting) {
        this.categoryName = categoryName;
        this.title = posting.getTitle();
        this.userName = userName;
        this.viewCount = posting.getViewCount();
    }
}
