package com.project.githering.Chatting.ChatRoom.DTO;

import com.project.githering.Chatting.ChatRoom.Entity.ChatRoom;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatRoomResponseDTO {

    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Long masterId;

    public ChatRoomResponseDTO(ChatRoom chatRoom) {
        this.id = chatRoom.getId();
        this.name = chatRoom.getName();
        this.masterId = chatRoom.getMasterId();
    }
}
