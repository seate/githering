package com.project.githering.Board.Posting.Repository;

import com.project.githering.Board.Posting.Entity.Posting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
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

    Page<Posting> findAllByGroupId(Long groupId, Pageable pageable);

    Page<Posting> findAllByCategoryId(Long categoryId, Pageable pageable);

    Page<Posting> findAllByUserId(Long userId, Pageable pageable);
}
