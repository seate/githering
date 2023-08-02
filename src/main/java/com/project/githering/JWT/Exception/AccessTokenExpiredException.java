package com.project.githering.JWT.Exception;

import com.project.githering.Common.Exception.RuntimeExceptionWithHttpStatus;
import org.springframework.http.HttpStatus;

public class AccessTokenExpiredException extends RuntimeExceptionWithHttpStatus {
    public AccessTokenExpiredException() {
        super("accessToken이 만료되었습니다.", HttpStatus.UNAUTHORIZED);
    }
}
