package com.project.githering.JWT.Exception;

import com.project.githering.Common.Exception.RuntimeExceptionWithHttpStatus;
import org.springframework.http.HttpStatus;

public class RefreshTokenNotExistException extends RuntimeExceptionWithHttpStatus {
    public RefreshTokenNotExistException() {
        super("refreshToken이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
    }
}
