package com.solution.loginSolution.User.OAuth2.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OAuth2ExceptionHandler {

    @ExceptionHandler(AuthServerNotSupportedException.class)
    public ResponseEntity<String> authServerNotSupported(AuthServerNotSupportedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
