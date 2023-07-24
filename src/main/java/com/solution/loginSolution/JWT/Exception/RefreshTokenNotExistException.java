package com.solution.loginSolution.JWT.Exception;

public class RefreshTokenNotExistException extends RuntimeException {
    public RefreshTokenNotExistException() {
        super("refreshToken이 존재하지 않습니다.");
    }
}
