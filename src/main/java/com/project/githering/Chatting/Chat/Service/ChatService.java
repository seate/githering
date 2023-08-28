package com.project.githering.Chatting.Chat.Service;

import com.project.githering.Chatting.Chat.DTO.ChatResponseListDTO;
import com.project.githering.Chatting.Chat.DTO.UpdateChatRequestDTO;
import com.project.githering.Chatting.Chat.Entity.Chat;

public interface ChatService {

    //CREATE
    Long saveChatting(Chat chat);

    //DELETE
    void deleteChatting(Long userId, Long chatId);

    void deleteByChatRoomId(Long userId, Long chatRoomId);

    //READ
    ChatResponseListDTO findByChatRoomId(Long userId, Long chatRoomId);

    //UPDATE
    void updateChatting(Long userId, UpdateChatRequestDTO updateChatRequestDTO);
}
