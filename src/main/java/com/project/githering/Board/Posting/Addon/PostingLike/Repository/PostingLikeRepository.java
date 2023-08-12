package com.project.githering.Board.Posting.Addon.PostingLike.Repository;

import com.project.githering.Board.Posting.Addon.PostingLike.Entity.PostingLike;
import com.project.githering.Board.Posting.Addon.PostingLike.Entity.PostingLikePk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostingLikeRepository extends JpaRepository<PostingLike, PostingLikePk> {

    //CREATE
    @Override
    <S extends PostingLike> S save(S entity);

    //DELETE
    void deleteByPostingIdAndUserId(Long postingId, Long userId);

    //READ
    @Override
    boolean existsById(PostingLikePk postingLikePk);

    Optional<PostingLike> findByPostingIdAndUserId(Long postingId, Long userId);

    Integer countByPostingIdAndIsLikedDisliked(Long postingId, boolean isLikedDisliked);
}
