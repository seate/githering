package com.solution.loginSolution.User.OAuth2.Exception;

import com.solution.loginSolution.Common.Exception.RuntimeExceptionWithHttpStatus;
import org.springframework.http.HttpStatus;

public class AuthServerNotSupportedException extends RuntimeExceptionWithHttpStatus {
    public AuthServerNotSupportedException() {
        super("지원하지 않는 OAuth2 인증 서버입니다.", HttpStatus.BAD_REQUEST);
    }
}
