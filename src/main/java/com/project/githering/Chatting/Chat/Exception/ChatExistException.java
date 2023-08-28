package com.project.githering.Chatting.Chat.Exception;

import com.project.githering.Common.Exception.RuntimeExceptionWithHttpStatus;
import org.springframework.http.HttpStatus;

public class ChatExistException extends RuntimeExceptionWithHttpStatus {
    public ChatExistException() {
        super("이미 존재하는 채팅입니다.", HttpStatus.CONFLICT);
    }
}
