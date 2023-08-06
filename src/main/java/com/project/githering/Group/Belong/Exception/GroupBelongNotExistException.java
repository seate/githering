package com.project.githering.Group.Belong.Exception;

import com.project.githering.Common.Exception.RuntimeExceptionWithHttpStatus;
import org.springframework.http.HttpStatus;

public class GroupBelongNotExistException extends RuntimeExceptionWithHttpStatus {
    public GroupBelongNotExistException() {
        super("존재하지 않는 그룹입니다.", HttpStatus.NOT_FOUND);
    }
}
