package com.project.githering.Chatting.ChatRoom.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private Long masterId;

    @Column(nullable = false)
    private String name;

    public ChatRoom(Long masterId, String name) {
        this.masterId = masterId;
        this.name = name;
    }
}
