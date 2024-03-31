package com.ssafy.pennypal.domain.chat.entity;

import com.ssafy.pennypal.domain.member.entity.Member;
import com.ssafy.pennypal.domain.team.entity.Team;
import com.ssafy.pennypal.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_id")
    private Long chatRoomId;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    private List<ChatMessage> chatMessages = new ArrayList<>();

    @OneToMany(mappedBy = "memberChatRoom")
    private List<Member> members = new ArrayList<>();

    @OneToOne(mappedBy = "chatRoom")
    private Team team;

    @Builder
    public ChatRoom(List<ChatMessage> chatMessages, Member member, Team team) {
        this.chatMessages = chatMessages;
        this.members.add(member);
        this.team = team;
    }
}
