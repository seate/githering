package com.project.githering.Chatting.ChatRoom.Repository;

import com.project.githering.Chatting.ChatRoom.Entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    //CREATE
    @Override
    <S extends ChatRoom> S save(S entity);

    //DELETE
    void deleteById(Long id);

    //READ
    @Override
    Optional<ChatRoom> findById(Long id);
}
