package com.project.githering.Chatting.ChatRoom.ChatRoomBelong.Entity;

import com.project.githering.Common.Entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@IdClass(ChatRoomBelongPk.class)
public class ChatRoomBelong extends BaseTimeEntity {

    @Id
    @Column(nullable = false)
    private Long chatRoomId;

    @Id
    @Column(nullable = false)
    private Long userId;

    public ChatRoomBelong(Long chatRoomId, Long userId) {
        this.chatRoomId = chatRoomId;
        this.userId = userId;
    }
}
