package com.solution.loginSolution.JWT.Exception;

import com.solution.loginSolution.JWT.Controller.RefreshTokenController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = RefreshTokenController.class)
public class JwtExceptionHandler {
    @ExceptionHandler(TokenNotValidException.class)
    public ResponseEntity<String> TokenNotValid(TokenNotValidException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(RefreshTokenExpiredException.class)
    public ResponseEntity<String> RefreshTokenExpired(RefreshTokenExpiredException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(RefreshTokenNotExistException.class)
    public ResponseEntity<String> RefreshTokenNotExist(RefreshTokenNotExistException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> unhandledException(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>("예상치 못한 에러가 발생했습니다.", HttpStatus.FORBIDDEN);
    }
}
