package com.project.githering.Chatting.ChatRoom.ChatRoomBelong.Repository;

import com.project.githering.Chatting.ChatRoom.ChatRoomBelong.Entity.ChatRoomBelong;
import com.project.githering.Chatting.ChatRoom.ChatRoomBelong.Entity.ChatRoomBelongPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRoomBelongRepository extends JpaRepository<ChatRoomBelong, ChatRoomBelongPk> {

    //CREATE
    @Override
    <S extends ChatRoomBelong> S save(S entity);

    //DELETE
    void deleteByChatRoomIdAndUserId(Long chatRoomId, Long userId);

    void deleteAllByChatRoomId(Long chatRoomId);

    //READ
    Boolean existsByChatRoomIdAndUserId(Long chatRoomId, Long userId);

    List<Long> findAllChatRoomIdByUserId(Long userId);

    List<Long> findAllUserIdByChatRoomId(Long chatRoomId);
}
