package com.project.githering.Board.Posting.Addon.Comment.Controller;

import com.project.githering.Board.Posting.Addon.Comment.DTO.CommentResponseListDTO;
import com.project.githering.Board.Posting.Addon.Comment.DTO.CreateCommentRequestDTO;
import com.project.githering.Board.Posting.Addon.Comment.DTO.UpdateCommentRequestDTO;
import com.project.githering.Board.Posting.Addon.Comment.Service.CommentService;
import com.project.githering.User.General.Service.GeneralUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    private final GeneralUserService generalUserService;

    @PostMapping
    public ResponseEntity<Void> saveComment(@RequestBody @Valid CreateCommentRequestDTO createCommentRequestDTO) {
        Long userId = generalUserService.findIdByAuthentication();
        commentService.saveComment(userId, createCommentRequestDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        Long userId = generalUserService.findIdByAuthentication();
        commentService.deleteComment(userId, commentId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{postingId}")
    public ResponseEntity<CommentResponseListDTO> findAllCommentByPostingId(@PathVariable Long postingId) {
        return new ResponseEntity<>(commentService.findAllCommentByPostingId(postingId), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<Void> updateComment(@RequestBody @Valid UpdateCommentRequestDTO updateCommentRequestDTO) {
        Long userId = generalUserService.findIdByAuthentication();
        commentService.updateComment(userId, updateCommentRequestDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}