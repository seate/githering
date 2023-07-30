package com.solution.loginSolution.User.OAuth2.Exception;

public class AuthServerNotSupportedException extends RuntimeException {
    public AuthServerNotSupportedException() {
        super("지원하지 않는 OAuth2 인증 서버입니다.");
    }
}
