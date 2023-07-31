package com.solution.loginSolution.User.General.Exception;

import com.solution.loginSolution.Common.Exception.RuntimeExceptionWithHttpStatus;
import org.springframework.http.HttpStatus;

public class UserNotExistException extends RuntimeExceptionWithHttpStatus {
    public UserNotExistException() {
        super("존재하지 않는 회원입니다.", HttpStatus.NOT_FOUND);
    }
}
