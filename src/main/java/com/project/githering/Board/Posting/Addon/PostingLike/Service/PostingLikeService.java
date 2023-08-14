package com.project.githering.Board.Posting.Addon.PostingLike.Service;


import com.project.githering.Board.Posting.Addon.PostingLike.Entity.PostingLike;

import java.util.Optional;

public interface PostingLikeService {

    //CREATE, UPDATE
    void saveLike(Long postingId, Long userId);

    void saveDislike(Long postingId, Long userId);


    //DELETE
    void deletePostingLike(Long postingId, Long userId);


    //READ

    Optional<PostingLike> findByPostingIdAndUserId(Long postingId, Long userId);

    Integer countPostingLike(Long postingId);

    Integer countPostingDislike(Long postingId);
}
