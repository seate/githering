package com.project.githering.Board.Posting.DTO;

import com.project.githering.Board.Posting.Entity.Posting;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostingRequestDTO {

    @NotNull
    private Long groupId;

    @NotNull
    private Long categoryId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    public Posting toEntity(Long userId) {
        return Posting.builder()
                .groupId(groupId)
                .categoryId(categoryId)
                .title(title)
                .userId(userId)
                .content(content)
                .build();
    }
}
