package com.ssafy.pennypal.domain.chat.dto;

import com.ssafy.pennypal.domain.chat.entity.ChatMessage;
import com.ssafy.pennypal.domain.chat.entity.ChatRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ChatRoomDto {

    private Long chatRoomId;

    private List<SimpleMemberDto> members;

    private List<ChatMessageDto> messages;

    @Builder
    private ChatRoomDto(Long chatRoomId, List<SimpleMemberDto> members, List<ChatMessageDto> messages) {
        this.chatRoomId = chatRoomId;
        this.members = members;
        this.messages = messages;
    }

    public static ChatRoomDto convertToChatRoomDto(ChatRoom chatRoom, List<ChatMessage> chatMessages) {

        List<ChatMessageDto> messageDto = chatMessages.stream()
                .map(ChatMessageDto::convertToChatMessageDto)
                .collect(Collectors.toList());

        // 멤버들을 SimpleMemberDto로 변환하고 ChatRoomDto를 구성
        return ChatRoomDto.builder()
                .chatRoomId(chatRoom.getChatRoomId())
                .members(chatRoom.getMembers().stream()
                        .map(SimpleMemberDto::convertToSimpleMemberDto)
                        .collect(Collectors.toList()))
                .messages(messageDto)
                .build();
    }
}
