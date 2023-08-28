package com.project.githering.Chatting.ChatRoom.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ChatRoomExceptionHandler {

    @ExceptionHandler(ChatRoomExistException.class)
    public ResponseEntity<String> chatRoomExist(ChatRoomExistException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }

    @ExceptionHandler(ChatRoomNotExistException.class)
    public ResponseEntity<String> chatRoomNotExist(ChatRoomNotExistException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }
}
