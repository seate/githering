package com.project.githering.Chatting.Chat.DTO;

import com.project.githering.Chatting.Chat.Entity.Chat;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateChatRequestDTO {

    @NotBlank
    private String message;


    public Chat toEntity(Long userId, Long chatRoomId) {
        return Chat.builder()
                .chatRoomId(chatRoomId)
                .userId(userId)
                .message(message)
                .regDate(LocalDateTime.now())
                .build();
    }
}
