package com.project.githering.Group.Belong.Exception;

import com.project.githering.Group.Exception.NoAuthorityException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GroupBelongExceptionHandler {

    @ExceptionHandler(GroupBelongExistException.class)
    public ResponseEntity<String> groupExist(GroupBelongExistException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }

    @ExceptionHandler(GroupBelongNotExistException.class)
    public ResponseEntity<String> groupNotExist(GroupBelongNotExistException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }

    @ExceptionHandler(NoAuthorityException.class)
    public ResponseEntity<String> noAuthority(NoAuthorityException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }

    @ExceptionHandler(AlreadyJoinedException.class)
    public ResponseEntity<String> alreadyJoined(AlreadyJoinedException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }

    @ExceptionHandler(NotJoinedException.class)
    public ResponseEntity<String> notJoined(NotJoinedException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }
}
