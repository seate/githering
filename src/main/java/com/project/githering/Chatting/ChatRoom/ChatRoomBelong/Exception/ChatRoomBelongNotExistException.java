package com.project.githering.Chatting.ChatRoom.ChatRoomBelong.Exception;

import com.project.githering.Common.Exception.RuntimeExceptionWithHttpStatus;
import org.springframework.http.HttpStatus;

public class ChatRoomBelongNotExistException extends RuntimeExceptionWithHttpStatus {
    public ChatRoomBelongNotExistException() {
        super("채팅방에 가입되지 않았습니다.", HttpStatus.NOT_FOUND);
    }
}
