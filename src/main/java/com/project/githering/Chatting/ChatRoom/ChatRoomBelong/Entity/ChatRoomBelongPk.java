package com.project.githering.Chatting.ChatRoom.ChatRoomBelong.Entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChatRoomBelongPk implements Serializable {
    private Long chatRoomId;
    private Long userId;
}
