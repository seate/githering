package com.solution.loginSolution.JWT.Exception;

import com.solution.loginSolution.Common.Exception.RuntimeExceptionWithHttpStatus;
import org.springframework.http.HttpStatus;

public class RefreshTokenNotExistException extends RuntimeExceptionWithHttpStatus {
    public RefreshTokenNotExistException() {
        super("refreshToken이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
    }
}
