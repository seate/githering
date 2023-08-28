package com.project.githering.Chatting.Chat.Service;

import com.project.githering.Chatting.Chat.DTO.ChatResponseListDTO;
import com.project.githering.Chatting.Chat.DTO.UpdateChatRequestDTO;
import com.project.githering.Chatting.Chat.Entity.Chat;
import com.project.githering.Chatting.Chat.Exception.ChatNotExistException;
import com.project.githering.Chatting.Chat.Repository.ChatRepository;
import com.project.githering.Chatting.ChatRoom.Exception.NoAuthorityException;
import com.project.githering.Chatting.ChatRoom.Service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;

    private final ChatRoomService chatRoomService;

    @Override
    @Transactional
    public Long saveChatting(Chat chat) {
        return chatRepository.save(chat).getId();
    }

    @Override
    @Transactional
    public void deleteChatting(Long userId, Long chatId) {
        if (!isMaster(userId, chatId)) throw new NoAuthorityException();

        chatRepository.deleteById(chatId);
    }

    @Override
    @Transactional
    public void deleteByChatRoomId(Long userId, Long chatRoomId) {
        if (!chatRoomService.isMaster(userId, chatRoomId)) throw new NoAuthorityException();

        chatRepository.deleteByChatRoomId(chatRoomId);
    }

    private Optional<Chat> findById(Long chatId) {
        return chatRepository.findById(chatId);
    }

    private Boolean isMaster(Long userId, Long chatId) {
        return findById(chatId).orElseThrow(ChatNotExistException::new).getUserId().equals(userId);
    }

    @Override
    public ChatResponseListDTO findByChatRoomId(Long userId, Long chatRoomId) {
        if (!chatRoomService.isMember(userId, chatRoomId)) throw new NoAuthorityException();

        return new ChatResponseListDTO(chatRepository.findAllByChatRoomId(chatRoomId));
    }

    @Override
    @Transactional
    public void updateChatting(Long userId, UpdateChatRequestDTO updateChatRequestDTO) {
        Chat chat = chatRepository.findById(updateChatRequestDTO.getChatId())
                .orElseThrow(ChatNotExistException::new);

        if (!chat.getUserId().equals(userId)) throw new NoAuthorityException();

        chat.setMessage(updateChatRequestDTO.getMessage());
    }
}
