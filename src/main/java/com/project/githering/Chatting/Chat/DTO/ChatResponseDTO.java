package com.project.githering.Chatting.Chat.DTO;

import com.project.githering.Chatting.Chat.Entity.Chat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ChatResponseDTO {

    @NotNull
    private Long id;

    @NotNull
    private Long chatRoomId;

    @NotNull
    private Long userId;

    @NotBlank
    private String message;

    @NotNull
    private LocalDateTime regDate;

    public ChatResponseDTO(Chat chat) {
        this.id = chat.getId();
        this.chatRoomId = chat.getChatRoomId();
        this.userId = chat.getUserId();
        this.message = chat.getMessage();
        this.regDate = chat.getRegDate();
    }
}
