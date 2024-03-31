package com.ssafy.pennypal.domain.chat.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EnterChatRoomRequest {

    private Long chatRoomId;
    private Long memberId;

    @Builder
    private EnterChatRoomRequest(Long chatRoomId, Long memberId) {
        this.chatRoomId = chatRoomId;
        this.memberId = memberId;
    }
}
