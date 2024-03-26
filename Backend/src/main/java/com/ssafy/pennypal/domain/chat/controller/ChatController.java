package com.ssafy.pennypal.domain.chat.controller;

import com.ssafy.pennypal.domain.chat.dto.ChatRoomDto;
import com.ssafy.pennypal.domain.chat.dto.request.ChattingRequest;
import com.ssafy.pennypal.domain.chat.dto.request.EnterChatRoomRequest;
import com.ssafy.pennypal.domain.chat.service.ChatService;
import com.ssafy.pennypal.global.common.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/chat")
public class ChatController {

    private final ChatService chatService;

    /**
     * note : 2.7.2 참여 중인 채팅방 상세 조회
     */
    @GetMapping("/enter")
    public ApiResponse<ChatRoomDto> enterChatRoom(@RequestParam("chatRoomId") Long chatRoomId,
                                                  @RequestParam("memberId") Long memberId){
        return ApiResponse.ok(chatService.enterChatRoom(chatRoomId, memberId));
    }

    /**
     * note : 2.7.3 채팅 전송
     */
    @MessageMapping("/{roomId}")
    public void chatMessageSend(@DestinationVariable("roomId") Long roomId,
                                @RequestBody ChattingRequest request) {

        chatService.sendChatMessage(roomId, request);
    }


}
