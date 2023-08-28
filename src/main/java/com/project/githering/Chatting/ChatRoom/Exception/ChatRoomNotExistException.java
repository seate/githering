package com.project.githering.Chatting.ChatRoom.Exception;

import com.project.githering.Common.Exception.RuntimeExceptionWithHttpStatus;
import org.springframework.http.HttpStatus;

public class ChatRoomNotExistException extends RuntimeExceptionWithHttpStatus {
    public ChatRoomNotExistException() {
        super("존재하지 않는 채팅방입니다.", HttpStatus.NOT_FOUND);
    }
}
