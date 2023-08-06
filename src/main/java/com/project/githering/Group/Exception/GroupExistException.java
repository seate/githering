package com.project.githering.Group.Exception;

import com.project.githering.Common.Exception.RuntimeExceptionWithHttpStatus;
import org.springframework.http.HttpStatus;

public class GroupExistException extends RuntimeExceptionWithHttpStatus {
    public GroupExistException() {
        super("이미 존재하는 그룹입니다.", HttpStatus.CONFLICT);
    }
}
