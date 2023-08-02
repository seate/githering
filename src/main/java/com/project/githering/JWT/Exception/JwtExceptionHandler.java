package com.project.githering.JWT.Exception;

import com.project.githering.JWT.Controller.RefreshTokenController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = RefreshTokenController.class)
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
