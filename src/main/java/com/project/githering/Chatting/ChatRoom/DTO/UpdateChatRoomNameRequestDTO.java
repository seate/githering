package com.project.githering.Chatting.ChatRoom.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateChatRoomNameRequestDTO {

    @NotNull
    private Long chatRoomId;

    @NotBlank
    private String chatRoomName;
}
