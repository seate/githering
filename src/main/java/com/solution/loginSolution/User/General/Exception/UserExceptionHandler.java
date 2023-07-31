package com.solution.loginSolution.User.General.Exception;

import com.solution.loginSolution.JWT.Exception.AccessTokenNotExistException;
import com.solution.loginSolution.User.General.Controller.UserController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = UserController.class)
public class UserExceptionHandler {
    
    @ExceptionHandler(loginFailureException.class)
    public ResponseEntity<String> loginFailure(loginFailureException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }

    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<String> userExist(UserExistException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }

    @ExceptionHandler(UserNotExistException.class)
    public ResponseEntity<String> userNotExist(UserNotExistException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }

    @ExceptionHandler(PasswordNotMatchException.class)
    public ResponseEntity<String> passwordNotMatch(PasswordNotMatchException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }

    @ExceptionHandler(AccessTokenNotExistException.class)
    public ResponseEntity<String> accessTokenNotExist(AccessTokenNotExistException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }
}
