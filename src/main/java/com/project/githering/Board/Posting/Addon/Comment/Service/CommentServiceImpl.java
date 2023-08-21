package com.project.githering.Board.Posting.Addon.Comment.Service;

import com.project.githering.Board.Posting.Addon.Comment.DTO.CommentResponseDTO;
import com.project.githering.Board.Posting.Addon.Comment.DTO.CommentResponseListDTO;
import com.project.githering.Board.Posting.Addon.Comment.DTO.CreateCommentRequestDTO;
import com.project.githering.Board.Posting.Addon.Comment.Entity.Comment;
import com.project.githering.Board.Posting.Addon.Comment.Exception.CommentNotExistException;
import com.project.githering.Board.Posting.Addon.Comment.Repository.CommentRepository;
import com.project.githering.Group.Exception.NoAuthorityException;
import com.project.githering.User.General.Exception.UserNotExistException;
import com.project.githering.User.General.Service.GeneralUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final GeneralUserService generalUserService;

    @Override
    public Long saveComment(Long userId, CreateCommentRequestDTO createCommentRequestDTO) {

        Long parentCommentId = createCommentRequestDTO.getParentCommentId();
        if (parentCommentId != null) {
            Long postingId = findCommentById(parentCommentId)
                    .orElseThrow(CommentNotExistException::new)
                    .getPostingId();

            if (!postingId.equals(createCommentRequestDTO.getPostingId()))
                throw new CommentNotExistException("부모 댓글의 게시글이 다릅니다.");
        }

        return commentRepository.save(createCommentRequestDTO.toEntity(userId)).getCommentId();
    }

    @Override
    public void deleteComment(Long userId, Long commentId) {
        Comment comment = findCommentById(commentId).orElseThrow(CommentNotExistException::new);
        if (!comment.getUserId().equals(userId)) throw new NoAuthorityException();
        commentRepository.delete(comment);
    }

    private Optional<Comment> findCommentById(Long commentId) {
        return commentRepository.findById(commentId);
    }

    @Override
    public CommentResponseListDTO findAllCommentByPostingId(Long postingId) {
        List<CommentResponseDTO> list = commentRepository.findAllByPostingId(postingId)
                .stream().map(comment -> {
                    String userName = generalUserService.findById(comment.getUserId())
                            .orElseThrow(UserNotExistException::new)
                            .getUserName();

                    return new CommentResponseDTO(userName, comment);
                })
                .toList();

        return new CommentResponseListDTO(list);
    }

    /*@Override
    public Page<CommentResponseDTO> findAllCommentByUserId(Long userId, Pageable pageable) {
        return commentRepository.findAllByUserId(userId, pageable)
                .map(comment -> {
                    String userName = generalUserService.findById(comment.getUserId())
                    .orElseThrow(UserNotExistException::new)
                    .getUserName();

                    return new CommentResponseDTO(userName, comment);
                });
    }*/
}
