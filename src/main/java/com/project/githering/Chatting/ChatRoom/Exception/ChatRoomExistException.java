package com.project.githering.Chatting.ChatRoom.Exception;

import com.project.githering.Common.Exception.RuntimeExceptionWithHttpStatus;
import org.springframework.http.HttpStatus;

public class ChatRoomExistException extends RuntimeExceptionWithHttpStatus {
    public ChatRoomExistException() {
        super("이미 존재하는 채팅방입니다.", HttpStatus.CONFLICT);
    }
}
