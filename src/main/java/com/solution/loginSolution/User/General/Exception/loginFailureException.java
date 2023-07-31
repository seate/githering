package com.solution.loginSolution.User.General.Exception;

import com.solution.loginSolution.Common.Exception.RuntimeExceptionWithHttpStatus;
import org.springframework.http.HttpStatus;

public class loginFailureException extends RuntimeExceptionWithHttpStatus {
    public loginFailureException() {
        super("로그인에 실패하였습니다.", HttpStatus.UNAUTHORIZED);
    }
}
