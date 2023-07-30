package com.solution.loginSolution.User.General.Exception;

public class UserExistException extends RuntimeException {
    public UserExistException() {
        super("이미 존재하는 회원입니다.");
    }
}
