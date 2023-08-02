package com.project.githering.User.General.Exception;

import com.project.githering.Common.Exception.RuntimeExceptionWithHttpStatus;
import org.springframework.http.HttpStatus;

public class UserNotExistException extends RuntimeExceptionWithHttpStatus {
    public UserNotExistException() {
        super("존재하지 않는 회원입니다.", HttpStatus.NOT_FOUND);
    }
}
