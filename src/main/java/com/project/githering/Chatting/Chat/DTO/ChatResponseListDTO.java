package com.project.githering.Chatting.Chat.DTO;

import com.project.githering.Chatting.Chat.Entity.Chat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ChatResponseListDTO {

    @NotNull
    private List<ChatResponseDTO> chatResponseDTOList;

    public ChatResponseListDTO(List<Chat> chatList) {
        this.chatResponseDTOList = chatList.stream().map(ChatResponseDTO::new).toList();
    }
}