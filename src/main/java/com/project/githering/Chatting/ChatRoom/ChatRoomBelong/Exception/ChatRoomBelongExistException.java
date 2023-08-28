package com.project.githering.Chatting.ChatRoom.ChatRoomBelong.Exception;

import com.project.githering.Common.Exception.RuntimeExceptionWithHttpStatus;
import org.springframework.http.HttpStatus;

public class ChatRoomBelongExistException extends RuntimeExceptionWithHttpStatus {
    public ChatRoomBelongExistException() {
        super("이미 가입한 채팅방입니다.", HttpStatus.CONFLICT);
    }
}
