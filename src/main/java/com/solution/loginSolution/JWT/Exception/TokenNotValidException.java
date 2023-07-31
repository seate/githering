package com.solution.loginSolution.JWT.Exception;

import com.solution.loginSolution.Common.Exception.RuntimeExceptionWithHttpStatus;
import org.springframework.http.HttpStatus;

public class TokenNotValidException extends RuntimeExceptionWithHttpStatus {
    public TokenNotValidException() {
        super("유효하지 않은 토큰입니다.", HttpStatus.UNAUTHORIZED);
    }
}
