package com.solution.loginSolution.User.General.Exception;

import com.solution.loginSolution.JWT.Exception.AccessTokenNotExistException;
import com.solution.loginSolution.User.General.Controller.UserController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = UserController.class)
public class UserExceptionHandler {
    
    @ExceptionHandler(loginFailureException.class)
    public ResponseEntity<String> loginFailure(loginFailureException e) {
        return new ResponseEntity<>("로그인에 실패하였습니다.", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<String> userExist(UserExistException e) {
        return new ResponseEntity<>("이미 존재하는 회원입니다.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotExistException.class)
    public ResponseEntity<String> userNotExist(UserNotExistException e) {
        return new ResponseEntity<>("존재하지 않는 회원입니다.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordNotMatchException.class)
    public ResponseEntity<String> passwordNotMatch(PasswordNotMatchException e) {
        return new ResponseEntity<>("비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessTokenNotExistException.class)
    public ResponseEntity<String> accessTokenNotExist(AccessTokenNotExistException e) {
        return new ResponseEntity<>("accessToken이 존재하지 않습니다.", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> unHandledError(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>("예상치 못한 에러가 발생했습니다.", HttpStatus.NOT_ACCEPTABLE);
    }
}
