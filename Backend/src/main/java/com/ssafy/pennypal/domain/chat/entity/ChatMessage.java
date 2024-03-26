package com.ssafy.pennypal.domain.chat.entity;

import com.ssafy.pennypal.domain.member.entity.Member;
import com.ssafy.pennypal.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long chatMessageId;

    private Long senderId;

    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;


    @Builder
    private ChatMessage(Long senderId, String message, ChatRoom chatRoom) {
        this.senderId = senderId;
        this.message = message;
        this.chatRoom = chatRoom;
    }
}
