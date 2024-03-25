package com.ssafy.pennypal.domain.chat.service;

import com.ssafy.pennypal.domain.chat.dto.ChatRoomDto;
import com.ssafy.pennypal.domain.chat.dto.request.ChattingRequest;
import com.ssafy.pennypal.domain.chat.entity.ChatMessage;
import com.ssafy.pennypal.domain.chat.entity.ChatRoom;
import com.ssafy.pennypal.domain.chat.repository.IChatMessageRepository;
import com.ssafy.pennypal.domain.chat.repository.IChatRoomRepository;
import com.ssafy.pennypal.domain.member.entity.Member;
import com.ssafy.pennypal.domain.member.repository.IMemberRepository;
import com.ssafy.pennypal.domain.team.entity.Team;
import com.ssafy.pennypal.domain.team.repository.ITeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatService {

    private final IMemberRepository memberRepository;
    private final IChatMessageRepository chatMessageRepository;
    private final IChatRoomRepository chatRoomRepository;
    private final ITeamRepository teamRepository;

    private final SimpMessagingTemplate simpMessagingTemplate;

    /**
     * 팀 생성하면 자동으로 팀 채팅방 생성
     */
    @Transactional
    public void createChatRoom(Long memberId) {

        Member member = getMember(memberId);

        ChatRoom newChatRoom = ChatRoom.builder()
                .member(member)
                .build();

        chatRoomRepository.save(newChatRoom);
        member.setChatRoom(newChatRoom);
    }

    /**
     * 팀 가입하면 팀 채팅방에 초대
     */
    @Transactional
    public void inviteChatRoom(Long teamId, Long memberId){

        Member member = getMember(memberId);
        Team team = teamRepository.findByTeamId(teamId);

        // 가입하려는 팀 팀장
        Long teamLeaderId = team.getTeamLeaderId();
        Member teamLeader = getMember(teamLeaderId);

        // 가입하려는 팀의 채팅방
        Long chatRoomId = teamLeader.getChatRoom().getChatRoomId();
        ChatRoom chatRoom = chatRoomRepository.findByChatRoomId(chatRoomId);

        // 유저 리스트에 추가, 유저 채팅방에 추가
        chatRoom.getMembers().add(member);
        member.setChatRoom(chatRoom);

        chatRoomRepository.save(chatRoom);
    }

    /**
     * 채팅방 입장
     */
    public ChatRoomDto enterChatRoom(Long chatRoomId){

        ChatRoom chatRoom = chatRoomRepository.findByChatRoomId(chatRoomId);

        // chatRoomId로 메시지들을 조회하는 메소드가 있다고 가정
        List<ChatMessage> chatMessages = chatMessageRepository.findByChatRoomId(chatRoomId);

        return ChatRoomDto.convertToChatRoomDto(chatRoom, chatMessages);
    }

    /**
     * 메시지 전송
     */
    @Transactional
    public void sendChatMessage(Long roomId, ChattingRequest request) {

        String destination = "/sub/chat/" + roomId;

        simpMessagingTemplate.convertAndSend(destination, request);

    }

    private Member getMember(Long memberId){
        return memberRepository.findByMemberId(memberId);
    }
}
