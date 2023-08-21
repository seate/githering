package com.project.githering.Board.Posting.Addon.Comment.Repository;

import com.project.githering.Board.Posting.Addon.Comment.Entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    //CREATE

    //DELETE

    //READ
    List<Comment> findAllByPostingId(Long postingId);

    Page<Comment> findAllByUserId(Long userId, Pageable pageable);
}
