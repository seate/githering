package com.project.githering.Chatting.ChatRoom.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteChatRoomRequestDTO {

    @NotNull
    private Long chatRoomId;
}
