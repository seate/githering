package com.project.githering.Group.Exception;

import com.project.githering.Common.Exception.RuntimeExceptionWithHttpStatus;
import org.springframework.http.HttpStatus;

public class GroupNotExistException extends RuntimeExceptionWithHttpStatus {
    public GroupNotExistException() {
        super("존재하지 않는 그룹입니다.", HttpStatus.NOT_FOUND);
    }
}
