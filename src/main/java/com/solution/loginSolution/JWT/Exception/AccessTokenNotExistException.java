package com.solution.loginSolution.JWT.Exception;

public class AccessTokenNotExistException extends RuntimeException {
    public AccessTokenNotExistException() {
        super("accessToken이 존재하지 않습니다.");
    }
}
