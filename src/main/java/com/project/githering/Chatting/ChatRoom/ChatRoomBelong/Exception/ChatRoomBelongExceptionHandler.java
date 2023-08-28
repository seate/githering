package com.project.githering.Chatting.ChatRoom.ChatRoomBelong.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ChatRoomBelongExceptionHandler {

    @ExceptionHandler(ChatRoomBelongExistException.class)
    public ResponseEntity<String> chatRoomExist(ChatRoomBelongExistException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }

    @ExceptionHandler(ChatRoomBelongNotExistException.class)
    public ResponseEntity<String> chatRoomExist(ChatRoomBelongNotExistException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }
}
