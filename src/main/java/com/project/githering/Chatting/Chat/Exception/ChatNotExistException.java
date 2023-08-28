package com.project.githering.Chatting.Chat.Exception;

import com.project.githering.Common.Exception.RuntimeExceptionWithHttpStatus;
import org.springframework.http.HttpStatus;

public class ChatNotExistException extends RuntimeExceptionWithHttpStatus {
    public ChatNotExistException() {
        super("존재하지 않는 채팅입니다.", HttpStatus.NOT_FOUND);
    }
}
