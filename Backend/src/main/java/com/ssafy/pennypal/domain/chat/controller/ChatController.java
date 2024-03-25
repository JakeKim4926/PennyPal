package com.ssafy.pennypal.domain.chat.controller;

import com.ssafy.pennypal.domain.chat.dto.ChatRoomDto;
import com.ssafy.pennypal.domain.chat.dto.request.ChattingRequest;
import com.ssafy.pennypal.domain.chat.service.ChatService;
import com.ssafy.pennypal.global.common.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    /**
     * note : 2.7.2 참여 중인 채팅방 상세 조회
     */
    @GetMapping("/chat/{rommId}")
    public ApiResponse<ChatRoomDto> enterChatRoom(@PathVariable Long chatRoomId){
        return ApiResponse.ok(chatService.enterChatRoom(chatRoomId));
    }

    /**
     * note : 2.7.3 채팅 전송
     */
    @MessageMapping("/chat/{roomId}")
    public void chatMessageSend(@DestinationVariable("roomId") Long roomId,
                                @RequestBody ChattingRequest request) {

        chatService.sendChatMessage(roomId, request);
    }


}
