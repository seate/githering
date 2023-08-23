package com.project.githering.Board.Posting.Addon.Comment.Service;


import com.project.githering.Board.Posting.Addon.Comment.DTO.CommentResponseListDTO;
import com.project.githering.Board.Posting.Addon.Comment.DTO.CreateCommentRequestDTO;
import com.project.githering.Board.Posting.Addon.Comment.DTO.UpdateCommentRequestDTO;

public interface CommentService {

    //CREATE, UPDATE
    Long saveComment(Long userId, CreateCommentRequestDTO createCommentRequestDTO);

    //DELETE
    void deleteComment(Long userId, Long commentId);

    //READ
    //Optional<Comment> findCommentById(Long commentId);

    CommentResponseListDTO findAllCommentByPostingId(Long postingId);

    //Page<CommentResponseDTO> findAllCommentByUserId(Long userId, Pageable pageable);

    //UPDATE
    void updateComment(Long userId, UpdateCommentRequestDTO updateCommentRequestDTO);
}
