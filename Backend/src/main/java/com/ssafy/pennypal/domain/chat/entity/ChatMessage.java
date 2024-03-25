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
    private Long chatId;

    @Column(name = "chat_message")
    private String chatMessage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @Builder
    private ChatMessage(String chatMessage, ChatRoom chatRoom) {
        this.chatMessage = chatMessage;
        this.chatRoom = chatRoom;
    }
}
