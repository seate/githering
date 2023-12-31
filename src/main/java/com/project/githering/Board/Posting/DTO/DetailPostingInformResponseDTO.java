package com.project.githering.Board.Posting.DTO;

import com.project.githering.Board.Posting.Addon.Comment.DTO.CommentResponseListDTO;
import com.project.githering.Board.Posting.Entity.Posting;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class DetailPostingInformResponseDTO {

    @NotNull
    private Long postingId;

    @NotNull
    private Long groupId;

    @NotBlank
    private String categoryName;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String userName;

    @NotNull
    private Integer viewCount;

    @NotNull
    private LocalDate createdDate;

    @NotNull
    private Integer likeCount;

    @NotNull
    private Integer dislikeCount;

    @NotNull
    private CommentResponseListDTO commentResponseListDTO;

    public DetailPostingInformResponseDTO(String categoryName, String userName, CommentResponseListDTO commentResponseListDTO, Posting posting) {
        this.postingId = posting.getPostingId();
        this.groupId = posting.getGroupId();
        this.categoryName = categoryName;
        this.title = posting.getTitle();
        this.content = posting.getContent();
        this.userName = userName;
        this.viewCount = posting.getViewCount();
        this.createdDate = posting.getCreateTime().toLocalDate();
        this.likeCount = posting.getLikeCount();
        this.dislikeCount = posting.getDislikeCount();
        this.commentResponseListDTO = commentResponseListDTO;
    }
}
