package com.project.githering.Board.Posting.Addon.PostingLike.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PostingLikeExceptionHandler {

    @ExceptionHandler(PostingLikeExistException.class)
    public ResponseEntity<String> postingExist(PostingLikeExistException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }

    @ExceptionHandler(PostingLikeNotExistException.class)
    public ResponseEntity<String> postingNotExist(PostingLikeNotExistException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }
}
