package com.ssafy.pennypal.domain.chat.dto;

import com.ssafy.pennypal.domain.chat.entity.ChatMessage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ChatMessageDto {

    private Long chatMessageId;
    private String memberNickname;
    private String message;
    private LocalDateTime createdAt;

    @Builder
    private ChatMessageDto(Long chatMessageId, String memberNickname, String message, LocalDateTime createdAt) {
        this.chatMessageId = chatMessageId;
        this.memberNickname = memberNickname;
        this.message = message;
        this.createdAt = createdAt;
    }

    public static ChatMessageDto convertToChatMessageDto(ChatMessage chatMessage){

        return ChatMessageDto.builder()
                .chatMessageId(chatMessage.getChatMessageId())
                .message(chatMessage.getMessage())
                .createdAt(chatMessage.getCreatedDateTime())
                .build();
    }
}
