package com.project.githering.Chatting.ChatRoom.DTO;

import com.project.githering.Chatting.ChatRoom.Entity.ChatRoom;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ChatRoomListResponseDTO {

    @NotNull
    private List<ChatRoomResponseDTO> chatRoomList;

    public ChatRoomListResponseDTO(List<ChatRoom> chatRoomList) {
        this.chatRoomList = chatRoomList.stream().map(ChatRoomResponseDTO::new).toList();
    }
}
