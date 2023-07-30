package com.solution.loginSolution.User.General.Exception;

public class UserNotExistException extends RuntimeException {
    public UserNotExistException() {
        super("존재하지 않는 회원입니다.");
    }
}
