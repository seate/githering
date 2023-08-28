package com.project.githering.Chatting.ChatRoom.Service;

import com.project.githering.Chatting.ChatRoom.ChatRoomBelong.Service.ChatRoomBelongService;
import com.project.githering.Chatting.ChatRoom.DTO.CreateChatRoomRequestDTO;
import com.project.githering.Chatting.ChatRoom.DTO.InviteChatRoomRequestDTO;
import com.project.githering.Chatting.ChatRoom.DTO.UpdateChatRoomMasterRequestDTO;
import com.project.githering.Chatting.ChatRoom.DTO.UpdateChatRoomNameRequestDTO;
import com.project.githering.Chatting.ChatRoom.Entity.ChatRoom;
import com.project.githering.Chatting.ChatRoom.Exception.ChatRoomNotExistException;
import com.project.githering.Chatting.ChatRoom.Exception.NoAuthorityException;
import com.project.githering.Chatting.ChatRoom.Repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    private final ChatRoomBelongService chatRoomBelongService;

    @Override
    @Transactional
    public Long createChatRoom(Long userId, CreateChatRoomRequestDTO createChatRoomRequestDTO) {
        Long chatRoomId = chatRoomRepository.save(createChatRoomRequestDTO.toEntity(userId)).getId();

        chatRoomBelongService.joinChatRoom(chatRoomId, userId);

        return chatRoomId;
    }

    @Override
    @Transactional
    public void inviteChatRoom(Long userId, InviteChatRoomRequestDTO inviteChatRoomRequestDTO) {
        if (!isMaster(userId, inviteChatRoomRequestDTO.getChatRoomId()))
            throw new NoAuthorityException();

        chatRoomBelongService.joinChatRoom(inviteChatRoomRequestDTO.getChatRoomId(), inviteChatRoomRequestDTO.getInviteUserId());
    }

    @Override
    @Transactional
    public void deleteChatRoomById(Long userId, Long chatRoomId) {
        if (!isMaster(userId, chatRoomId))
            throw new NoAuthorityException();

        chatRoomBelongService.deleteAllByChatRoomId(chatRoomId);

        chatRoomRepository.deleteById(chatRoomId);
    }

    @Override
    @Transactional
    public void leaveChatRoom(Long userId, Long chatRoomId) {
        if (!chatRoomBelongService.existsByChatRoomIdAndUserId(chatRoomId, userId))
            throw new ChatRoomNotExistException();

        chatRoomBelongService.withdrawalChatRoom(chatRoomId, userId);
    }

    @Override
    public Boolean isMaster(Long userId, Long chatRoomId) {
        return findChatRoomById(chatRoomId)
                .orElseThrow(ChatRoomNotExistException::new)
                .getMasterId().equals(userId);
    }

    @Override
    public Boolean isMember(Long userId, Long chatRoomId) {
        return chatRoomBelongService.existsByChatRoomIdAndUserId(chatRoomId, userId);
    }

    @Override
    public Optional<ChatRoom> findChatRoomById(Long chatRoomId) {
        return chatRoomRepository.findById(chatRoomId);
    }

    @Override
    public List<ChatRoom> findAllByUserId(Long userId) {
        return chatRoomRepository.findAllById(chatRoomBelongService.findAllChatRoomIdByUserId(userId));
    }

    @Override
    @Transactional
    public void updateMaster(Long userId, UpdateChatRoomMasterRequestDTO updateChatRoomMasterRequestDTO) {
        if (!isMaster(userId, updateChatRoomMasterRequestDTO.getChatRoomId()))
            throw new NoAuthorityException();

        findChatRoomById(updateChatRoomMasterRequestDTO.getChatRoomId())
                .orElseThrow(ChatRoomNotExistException::new)
                .setMasterId(updateChatRoomMasterRequestDTO.getNewMasterId());
    }

    @Override
    @Transactional
    public void updateName(Long userId, UpdateChatRoomNameRequestDTO updateChatRoomNameRequestDTO) {
        if (!isMaster(userId, updateChatRoomNameRequestDTO.getChatRoomId()))
            throw new NoAuthorityException();

        findChatRoomById(updateChatRoomNameRequestDTO.getChatRoomId())
                .orElseThrow(ChatRoomNotExistException::new)
                .setName(updateChatRoomNameRequestDTO.getChatRoomName());
    }
}
