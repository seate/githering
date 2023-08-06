package com.project.githering.Group.Belong.Exception;

import com.project.githering.Common.Exception.RuntimeExceptionWithHttpStatus;
import org.springframework.http.HttpStatus;

public class NotJoinedException extends RuntimeExceptionWithHttpStatus {
    public NotJoinedException() {
        super("가입되지 않은 그룹입니다.", HttpStatus.CONFLICT);
    }
}
