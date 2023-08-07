package com.project.githering.Board.Posting.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PostingExceptionHandler {

    @ExceptionHandler(PostingExistException.class)
    public ResponseEntity<String> postingExist(PostingExistException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }

    @ExceptionHandler(PostingNotExistException.class)
    public ResponseEntity<String> postingNotExist(PostingNotExistException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }
}
