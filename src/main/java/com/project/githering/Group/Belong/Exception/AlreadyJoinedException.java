package com.project.githering.Group.Belong.Exception;

import com.project.githering.Common.Exception.RuntimeExceptionWithHttpStatus;
import org.springframework.http.HttpStatus;

public class AlreadyJoinedException extends RuntimeExceptionWithHttpStatus {
    public AlreadyJoinedException() {
        super("이미 가입한 그룹입니다.", HttpStatus.CONFLICT);
    }
}
