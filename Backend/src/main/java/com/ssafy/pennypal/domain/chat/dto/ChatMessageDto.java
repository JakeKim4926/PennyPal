package com.ssafy.pennypal.domain.chat.dto;

import com.ssafy.pennypal.domain.chat.entity.ChatMessage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ChatMessageDto {

    private Long chatMessageId;
    private Long chatRoomId;
    private String memberNickname;
    private String message;
    private LocalDate createdAt;

    @Builder
    private ChatMessageDto(Long chatMessageId, Long chatRoomId, String memberNickname, String message, LocalDate createdAt) {
        this.chatMessageId = chatMessageId;
        this.chatRoomId = chatRoomId;
        this.memberNickname = memberNickname;
        this.message = message;
        this.createdAt = createdAt;
    }

    public static ChatMessageDto convertToChatMessageDto(ChatMessage chatMessage){

        return ChatMessageDto.builder()
                .chatMessageId(chatMessage.getChatMessageId())
                .chatRoomId(chatMessage.getChatRoom().getChatRoomId())
                .memberNickname(chatMessage.getMember().getMemberNickname())
                .message(chatMessage.getMessage())
                .createdAt(chatMessage.getCreatedDateTime().toLocalDate())
                .build();
    }
}
