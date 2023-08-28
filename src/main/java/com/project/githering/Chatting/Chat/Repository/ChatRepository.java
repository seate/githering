package com.project.githering.Chatting.Chat.Repository;

import com.project.githering.Chatting.Chat.Entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    //CREATE
    @Override
    <S extends Chat> S save(S entity);

    //DELETE
    void deleteByChatRoomId(Long chatRoomId);

    //READ
    List<Chat> findAllByChatRoomId(Long chatRoomId);
}
