package com.project.githering.Chatting.ChatRoom.Service;

import com.project.githering.Chatting.ChatRoom.DTO.CreateChatRoomRequestDTO;
import com.project.githering.Chatting.ChatRoom.DTO.InviteChatRoomRequestDTO;
import com.project.githering.Chatting.ChatRoom.DTO.UpdateChatRoomMasterRequestDTO;
import com.project.githering.Chatting.ChatRoom.DTO.UpdateChatRoomNameRequestDTO;
import com.project.githering.Chatting.ChatRoom.Entity.ChatRoom;

import java.util.List;
import java.util.Optional;

public interface ChatRoomService {

    //CREATE
    Long createChatRoom(Long userId, CreateChatRoomRequestDTO createChatRoomRequestDTO);

    void inviteChatRoom(Long userId, InviteChatRoomRequestDTO inviteChatRoomRequestDTO);

    //DELETE
    void deleteChatRoomById(Long userId, Long chatRoomId);

    void leaveChatRoom(Long userId, Long chatRoomId);



    //READ
    Boolean isMaster(Long userId, Long chatRoomId);

    Boolean isMember(Long userId, Long chatRoomId);

    Optional<ChatRoom> findChatRoomById(Long chatRoomId);

    List<ChatRoom> findAllByUserId(Long userId);


    //UPDATE
    void updateMaster(Long userId, UpdateChatRoomMasterRequestDTO updateChatRoomMasterRequestDTO);

    void updateName(Long userId, UpdateChatRoomNameRequestDTO updateChatRoomNameRequestDTO);
}
