package com.project.githering.Board.Posting.Repository;

import com.project.githering.Board.Posting.Entity.Posting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostingRepository extends JpaRepository<Posting, Long> {

    //CREATE
    @Override
    <S extends Posting> S save(S entity);

    //DELETE
    void deleteByPostingId(Long postingId);

    //READ
    Optional<Posting> findByPostingId(Long postingId);

    Optional<Integer> findLikeCountByPostingId(Long postingId);

    Optional<Integer> findDislikeCountByPostingId(Long postingId);

    Page<Posting> findAllByGroupId(Long groupId, Pageable pageable);

    Page<Posting> findAllByCategoryId(Long categoryId, Pageable pageable);

    Page<Posting> findAllByUserId(Long userId, Pageable pageable);

    //UPDATE
    @Query("UPDATE Posting p SET p.likeCount = ?2 WHERE p.postingId = ?1")
    void updateLikeCount(Long postingId, Integer likeCount);

    @Query("UPDATE Posting p SET p.dislikeCount = ?2 WHERE p.postingId = ?1")
    void updateDislikeCount(Long postingId, Integer dislikeCount);
}
