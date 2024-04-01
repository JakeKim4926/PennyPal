package com.ssafy.pennypal.domain.chat.service;

import com.ssafy.pennypal.domain.chat.dto.ChatMessageDto;
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

import java.time.LocalDateTime;
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
        Team team = member.getTeam();

        ChatRoom newChatRoom = ChatRoom.builder()
                .member(member)
                .team(team)
                .build();

        chatRoomRepository.save(newChatRoom);

        team.setChatRoom(newChatRoom);
        teamRepository.save(team);

        member.setMemberChatRoom(newChatRoom);
        memberRepository.save(member);
    }

    /**
     * 팀 가입하면 팀 채팅방에 초대
     */
    @Transactional
    public void inviteChatRoom(Long teamId, Long memberId) {

        Member member = getMember(memberId);
        Team team = teamRepository.findByTeamId(teamId)
                .orElseThrow(() -> new IllegalArgumentException("팀 정보를 찾을 수 없습니다."));

        ChatRoom chatRoom = team.getChatRoom();

        // 유저 리스트에 추가, 유저 채팅방에 추가
        chatRoom.getMembers().add(member);
        member.setMemberChatRoom(chatRoom);

        chatRoomRepository.save(chatRoom);
    }

    /**
     * 채팅방 입장
     */
    public ChatRoomDto enterChatRoom(Long chatRoomId, Long memberId) {

        ChatRoom chatRoom = getChatRoom(chatRoomId);
        List<ChatMessage> chatMessages = chatMessageRepository.findByChatRoomId(chatRoomId);

        Member member = getMember(memberId); // 채팅방에 들어가려고 시도하는 유저
        List<Member> enableMembers = chatRoom.getMembers(); // 채팅방에 입장 가능한 유저 목록

        if (enableMembers.contains(member)) {
            return ChatRoomDto.convertToChatRoomDto(chatRoom, chatMessages);
        }else{
            throw new IllegalArgumentException("해당 팀 채팅에 접근 권한이 없습니다.");
        }

    }

    /**
     * 메시지 전송
     */
    @Transactional
    public ChatMessageDto sendChatMessage(Long roomId, ChattingRequest request) {

        String destination = "/sub/chat/" + roomId;

        Member member = getMember(request.getSenderId());
        ChatRoom chatRoom = getChatRoom(roomId);

        ChatMessage chatMessage = ChatMessage.builder()
                .senderId(request.getSenderId())
                .senderNickname(member.getMemberNickname())
                .message(request.getContent())
                .chatRoom(member.getMemberChatRoom())
                .createdAt(LocalDateTime.now())
                .build();
        chatMessageRepository.save(chatMessage);
        chatRoom.getChatMessages().add(chatMessage);

        ChatMessageDto messageDto = ChatMessageDto.convertToChatMessageDto(chatMessage);

        simpMessagingTemplate.convertAndSend(destination, messageDto);

        return messageDto;

    }

    private Member getMember(Long memberId) {
        return memberRepository.findByMemberId(memberId);
    }

    private ChatRoom getChatRoom(Long roomId){
        return chatRoomRepository.findByChatRoomId(roomId)
                .orElseThrow(() -> new IllegalArgumentException("해당 채팅방을 찾을 수 없습니다."));
    }
}
