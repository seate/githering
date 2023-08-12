package com.project.githering.Board.Posting.Addon.PostingLike.Service;


import com.project.githering.Board.Posting.Addon.PostingLike.Entity.PostingLike;

import java.util.Optional;

public interface PostingLikeService {

    //CREATE, UPDATE
    void saveOrUpdatePostingLike(Long postingId, Long userId, Boolean isLike);


    //DELETE
    void deletePostingLike(Long postingId, Long userId);


    //READ

    Optional<PostingLike> findByPostingIdAndUserId(Long postingId, Long userId);

    Integer countPostingLike(Long postingId);

    Integer countPostingDislike(Long postingId);
}
