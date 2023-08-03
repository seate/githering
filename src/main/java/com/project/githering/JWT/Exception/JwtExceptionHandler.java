package com.project.githering.JWT.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JwtExceptionHandler {
    @ExceptionHandler(TokenNotValidException.class)
    public ResponseEntity<String> TokenNotValid(TokenNotValidException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }

    @ExceptionHandler(RefreshTokenExpiredException.class)
    public ResponseEntity<String> RefreshTokenExpired(RefreshTokenExpiredException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }

    @ExceptionHandler(RefreshTokenNotExistException.class)
    public ResponseEntity<String> RefreshTokenNotExist(RefreshTokenNotExistException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }
}
