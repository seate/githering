package com.solution.loginSolution.JWT.Exception;

import com.solution.loginSolution.Common.Exception.RuntimeExceptionWithHttpStatus;
import org.springframework.http.HttpStatus;

public class RefreshTokenExpiredException extends RuntimeExceptionWithHttpStatus {
    public RefreshTokenExpiredException() {
        super("refreshToken이 만료되었습니다.", HttpStatus.UNAUTHORIZED);
    }
}
