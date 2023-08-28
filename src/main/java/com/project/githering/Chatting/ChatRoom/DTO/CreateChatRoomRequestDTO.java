package com.project.githering.Chatting.ChatRoom.DTO;

import com.project.githering.Chatting.ChatRoom.Entity.ChatRoom;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateChatRoomRequestDTO {

    @NotBlank
    private String chatRoomName;

    public ChatRoom toEntity(Long masterId) {
        return new ChatRoom(masterId, chatRoomName);
    }
}
