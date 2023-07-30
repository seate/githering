package com.solution.loginSolution.User.General.Exception;

public class loginFailureException extends RuntimeException {
    public loginFailureException() {
        super("로그인에 실패하였습니다.");
    }
}
