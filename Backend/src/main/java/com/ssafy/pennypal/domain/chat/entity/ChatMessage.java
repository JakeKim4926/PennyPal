package com.ssafy.pennypal.domain.chat.entity;

import com.ssafy.pennypal.domain.member.entity.Member;
import com.ssafy.pennypal.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long chatMessageId;

    private Long senderId;

    private String senderNickname;

    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    private LocalDateTime createdAt;


    @Builder
    private ChatMessage(Long senderId, String senderNickname, String message, ChatRoom chatRoom, LocalDateTime createdAt) {
        this.senderId = senderId;
        this.senderNickname = senderNickname;
        this.message = message;
        this.chatRoom = chatRoom;
        this.createdAt = createdAt;

    }
}
