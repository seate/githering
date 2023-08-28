package com.project.githering.Chatting.Chat.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateChatRequestDTO {

    @NotNull
    private Long chatId;

    @NotBlank
    private String message;
}