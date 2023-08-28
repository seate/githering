package com.project.githering.Chatting.ChatRoom.ChatRoomBelong.Service;

import com.project.githering.Chatting.ChatRoom.ChatRoomBelong.Entity.ChatRoomBelong;
import com.project.githering.Chatting.ChatRoom.ChatRoomBelong.Exception.ChatRoomBelongExistException;
import com.project.githering.Chatting.ChatRoom.ChatRoomBelong.Repository.ChatRoomBelongRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatRoomBelongServiceImpl implements ChatRoomBelongService {

    private final ChatRoomBelongRepository chatRoomBelongRepository;

    @Override
    @Transactional
    public void joinChatRoom(Long chatRoomId, Long userId) {
        if (existsByChatRoomIdAndUserId(chatRoomId, userId))
            throw new ChatRoomBelongExistException();

        chatRoomBelongRepository.save(new ChatRoomBelong(chatRoomId, userId));
    }

    @Override
    @Transactional
    public void withdrawalChatRoom(Long chatRoomId, Long userId) {
        if (!existsByChatRoomIdAndUserId(chatRoomId, userId))
            throw new ChatRoomBelongExistException();

        chatRoomBelongRepository.deleteByChatRoomIdAndUserId(chatRoomId, userId);
    }

    @Override
    @Transactional
    public void deleteAllByChatRoomId(Long chatRoomId) {
        chatRoomBelongRepository.deleteAllByChatRoomId(chatRoomId);
    }

    @Override
    public Boolean existsByChatRoomIdAndUserId(Long chatRoomId, Long userId) {
        return chatRoomBelongRepository.existsByChatRoomIdAndUserId(chatRoomId, userId);
    }

    @Override
    public List<Long> findAllChatRoomIdByUserId(Long userId) {
        return chatRoomBelongRepository.findAllChatRoomIdByUserId(userId);
    }

    @Override
    public List<Long> findAllUserIdByChatRoomId(Long chatRoomId) {
        return chatRoomBelongRepository.findAllUserIdByChatRoomId(chatRoomId);
    }
}
