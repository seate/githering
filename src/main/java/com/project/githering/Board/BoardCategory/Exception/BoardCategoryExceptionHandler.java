package com.project.githering.Board.BoardCategory.Exception;

import com.project.githering.Group.Exception.NoAuthorityException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BoardCategoryExceptionHandler {

    @ExceptionHandler(BoardCategoryExistException.class)
    public ResponseEntity<String> groupExist(BoardCategoryExistException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }

    @ExceptionHandler(BoardCategoryNotExistException.class)
    public ResponseEntity<String> groupNotExist(BoardCategoryNotExistException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }

    @ExceptionHandler(NoAuthorityException.class)
    public ResponseEntity<String> noAuthority(NoAuthorityException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }
}
