package com.project.githering.Group.Belong.Exception;

import com.project.githering.Common.Exception.RuntimeExceptionWithHttpStatus;
import org.springframework.http.HttpStatus;

public class GroupBelongExistException extends RuntimeExceptionWithHttpStatus {
    public GroupBelongExistException() {
        super("이미 존재하는 그룹입니다.", HttpStatus.CONFLICT);
    }
}
