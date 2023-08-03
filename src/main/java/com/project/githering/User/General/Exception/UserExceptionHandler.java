package com.project.githering.User.General.Exception;

import com.project.githering.JWT.Exception.AccessTokenNotExistException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {


    @ExceptionHandler(UserNotExistException.class)
    public ResponseEntity<String> userNotExist(UserNotExistException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }

    @ExceptionHandler(AccessTokenNotExistException.class)
    public ResponseEntity<String> accessTokenNotExist(AccessTokenNotExistException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }
}
