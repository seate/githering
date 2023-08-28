package com.project.githering.Chatting.ChatRoom.ChatRoomBelong.Service;


import java.util.List;

public interface ChatRoomBelongService {

    void joinChatRoom(Long chatRoomId, Long userId);

    void withdrawalChatRoom(Long chatRoomId, Long userId);

    void deleteAllByChatRoomId(Long chatRoomId);

    Boolean existsByChatRoomIdAndUserId(Long chatRoomId, Long userId);

    List<Long> findAllChatRoomIdByUserId(Long userId);

    List<Long> findAllUserIdByChatRoomId(Long chatRoomId);
}
