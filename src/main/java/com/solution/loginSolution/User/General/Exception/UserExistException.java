package com.solution.loginSolution.User.General.Exception;

import com.solution.loginSolution.Common.Exception.RuntimeExceptionWithHttpStatus;
import org.springframework.http.HttpStatus;

public class UserExistException extends RuntimeExceptionWithHttpStatus {
    public UserExistException() {
        super("이미 존재하는 회원입니다.", HttpStatus.CONFLICT);
    }
}
