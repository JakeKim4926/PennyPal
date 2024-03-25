package com.ssafy.pennypal.domain.chat.controller;

import com.ssafy.pennypal.domain.chat.dto.request.ChattingRequest;
import com.ssafy.pennypal.domain.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    /**
     * note : 2.7.3 채팅 전송
     */
    @MessageMapping("/chat/{roomId}")
    public void chatMessageSend(@DestinationVariable("roomId") Long roomId,
                                @RequestBody ChattingRequest request){

        chatService.sendChatMessage(roomId, request);
    }


}
