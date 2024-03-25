package com.ssafy.pennypal.domain.chat.controller;

import com.ssafy.pennypal.common.RestDocsSupport;
import com.ssafy.pennypal.domain.chat.dto.ChatMessageDto;
import com.ssafy.pennypal.domain.chat.dto.ChatRoomDto;
import com.ssafy.pennypal.domain.chat.dto.SimpleMemberDto;
import com.ssafy.pennypal.domain.chat.entity.ChatMessage;
import com.ssafy.pennypal.domain.chat.entity.ChatRoom;
import com.ssafy.pennypal.domain.chat.service.ChatService;
import com.ssafy.pennypal.domain.member.entity.Member;
import com.ssafy.pennypal.domain.team.service.TeamService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class ChatControllerTest extends RestDocsSupport {

    private final TeamService teamService = mock(TeamService.class);
    private final ChatService chatService = mock(ChatService.class);

    @Override
    protected Object initController() {
        return new ChatController(chatService);
    }

    @DisplayName("채팅방 상세 조회 (입장)")
    @Test
    void enterChatRoom() throws Exception{
        ChatRoom chatRoom = mock(ChatRoom.class);
        ChatMessage chatMessage = mock(ChatMessage.class);
        SimpleMemberDto memberDto = mock(SimpleMemberDto.class);
        ChatMessageDto messageDto = mock(ChatMessageDto.class);
        Member member = mock(Member.class);

        List<SimpleMemberDto> members = Arrays.asList(memberDto, memberDto);
        List<ChatMessageDto> messages = Arrays.asList(messageDto, messageDto);

        given(chatRoom.getChatRoomId()).willReturn(1L);
        given(chatMessage.getChatRoom()).willReturn(chatRoom);
        given(chatMessage.getChatMessageId()).willReturn(1L);
        given(chatMessage.getChatRoom().getChatRoomId()).willReturn(1L);

        given(memberDto.getMemberNickname()).willReturn("구성원 닉네임");
        given(messageDto.getChatMessageId()).willReturn(1L);
        given(messageDto.getMemberNickname()).willReturn("메시지 보낸 유저 닉네임");
        given(messageDto.getMessage()).willReturn("메시지 내용");
        given(messageDto.getCreatedAt()).willReturn(LocalDateTime.now());

        given(chatService.enterChatRoom(anyLong()))
                .willReturn(ChatRoomDto.builder()
                        .chatRoomId(1L)
                        .members(members)
                        .messages(messages)
                        .build());

        mockMvc.perform(
                        get("/api/chat/{chatRoomId}", 1L)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("enter-chat-room",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),

                                fieldWithPath("data").type(JsonFieldType.OBJECT)
                                        .description("응답 데이터"),
                                fieldWithPath("data.chatRoomId").type(JsonFieldType.NUMBER)
                                        .description("채팅방 ID"),

                                fieldWithPath("data.members").type(JsonFieldType.ARRAY)
                                        .description("채팅방 구성원"),
                                fieldWithPath("data.members[].memberId").type(JsonFieldType.NUMBER)
                                        .description("구성원 ID"),
                                fieldWithPath("data.members[].memberNickname").type(JsonFieldType.STRING)
                                        .description("구성원 닉네임"),

                                fieldWithPath("data.messages").type(JsonFieldType.ARRAY)
                                        .description("채팅방 메시지"),
                                fieldWithPath("data.messages[].chatMessageId").type(JsonFieldType.NUMBER)
                                        .description("해당 메시지 ID"),
                                fieldWithPath("data.messages[].memberNickname").type(JsonFieldType.STRING)
                                        .description("채팅 보낸 유저 닉네임"),
                                fieldWithPath("data.messages[].message").type(JsonFieldType.STRING)
                                        .description("메시지 내용"),
                                fieldWithPath("data.messages[].createdAt").type(JsonFieldType.ARRAY)
                                        .description("메시지 보낸 시간")
                        )

                ));

        verify(chatService).enterChatRoom(anyLong());
    }

}