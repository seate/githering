package com.project.githering.User.OAuth2.Exception;

import com.project.githering.Common.Exception.RuntimeExceptionWithHttpStatus;
import org.springframework.http.HttpStatus;

public class AuthServerNotSupportedException extends RuntimeExceptionWithHttpStatus {
    public AuthServerNotSupportedException() {
        super("지원하지 않는 OAuth2 인증 서버입니다.", HttpStatus.BAD_REQUEST);
    }
}
