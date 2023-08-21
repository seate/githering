package com.project.githering.Board.Posting.Addon.Comment.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommentExceptionHandler {

    @ExceptionHandler(CommentExistException.class)
    public ResponseEntity<String> commentExist(CommentExistException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }

    @ExceptionHandler(CommentNotExistException.class)
    public ResponseEntity<String> commentNotExist(CommentNotExistException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }
}
