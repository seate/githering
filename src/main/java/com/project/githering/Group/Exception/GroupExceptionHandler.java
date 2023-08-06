package com.project.githering.Group.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GroupExceptionHandler {

    @ExceptionHandler(GroupExistException.class)
    public ResponseEntity<String> groupExist(GroupExistException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }

    @ExceptionHandler(GroupNotExistException.class)
    public ResponseEntity<String> groupNotExist(GroupNotExistException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }

    @ExceptionHandler(NoAuthorityException.class)
    public ResponseEntity<String> noAuthority(NoAuthorityException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }
}
