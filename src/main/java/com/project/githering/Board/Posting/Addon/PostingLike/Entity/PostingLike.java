package com.project.githering.Board.Posting.Addon.PostingLike.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@IdClass(PostingLikePk.class)
public class PostingLike {

    @Id
    @Column(nullable = false)
    private Long postingId;

    @Id
    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Boolean isLikedDisliked;

    public PostingLike(Long postingId, Long userId, boolean isLikedDisliked) {
        this.postingId = postingId;
        this.userId = userId;
        this.isLikedDisliked = isLikedDisliked;
    }
}
