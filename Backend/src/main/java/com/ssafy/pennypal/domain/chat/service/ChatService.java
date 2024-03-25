package com.ssafy.pennypal.domain.chat.service;

import com.ssafy.pennypal.domain.chat.dto.request.ChattingRequest;
import com.ssafy.pennypal.domain.chat.entity.ChatRoom;
import com.ssafy.pennypal.domain.chat.repository.IChatMessageRepository;
import com.ssafy.pennypal.domain.chat.repository.IChatRoomRepository;
import com.ssafy.pennypal.domain.member.entity.Member;
import com.ssafy.pennypal.domain.member.repository.IMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatService {

    private final IMemberRepository memberRepository;
    private final IChatMessageRepository chatMessageRepository;
    private final IChatRoomRepository chatRoomRepository;

    private final SimpMessagingTemplate simpMessagingTemplate;

    /**
     * 팀 생성하면 자동으로 팀 채팅방 생성
     */
    @Transactional
    public void createChatRoom(Long memberId) {

        Member member = memberRepository.findByMemberId(memberId);

        ChatRoom newChatRoom = ChatRoom.builder()
                .member(member)
                .build();

        chatRoomRepository.save(newChatRoom);
        member.setChatRoom(newChatRoom);
    }


    /**
     * 메시지 전송
     */
    @Transactional
    public void sendChatMessage(Long roomId, ChattingRequest request) {

        String destination = "/sub/chat/" + roomId;

        simpMessagingTemplate.convertAndSend(destination, request);


    }
}
