package com.solution.loginSolution.User.General.Exception;

import com.solution.loginSolution.Common.Exception.RuntimeExceptionWithHttpStatus;
import org.springframework.http.HttpStatus;

public class PasswordNotMatchException extends RuntimeExceptionWithHttpStatus {
    public PasswordNotMatchException() {
        super("비밀번호가 일치하지 않습니다.", HttpStatus.CONFLICT);
    }
}
