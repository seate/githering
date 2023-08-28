package com.project.githering.Chatting.ChatRoom.Exception;

import com.project.githering.Common.Exception.RuntimeExceptionWithHttpStatus;
import org.springframework.http.HttpStatus;

public class NoAuthorityException extends RuntimeExceptionWithHttpStatus {
    public NoAuthorityException() {
        super("권한이 없습니다.", HttpStatus.UNAUTHORIZED);
    }

    public NoAuthorityException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
