package com.project.githering.Board.Posting.Addon.PostingLike.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
public class PostingLikePk implements Serializable {

    private Long postingId;

    private Long userId;
}
