package com.ssafy.pennypal.domain.member.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ssafy.pennypal.common.RestDocsSupport;
import com.ssafy.pennypal.domain.member.dto.request.MemberLoginRequest;
import com.ssafy.pennypal.domain.member.dto.request.MemberSignupRequest;
import com.ssafy.pennypal.domain.member.dto.response.MemberLoginResponse;
import com.ssafy.pennypal.domain.member.dto.response.MemberSignupResponse;
import com.ssafy.pennypal.domain.member.entity.Member;
import com.ssafy.pennypal.domain.member.service.MemberService;
import com.ssafy.pennypal.domain.team.controller.TeamControllerTest;
import com.ssafy.pennypal.global.common.api.ApiResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.transaction.annotation.Transactional;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class MemberControllerTest extends RestDocsSupport {

    private final MemberService memberService = mock(MemberService.class);

    @Override
    protected Object initController() {
        return new MemberController(memberService);
    }

    @DisplayName("멤버의 정보를 받아 회원가입을 진행한다.")
    @Test
    void signUp() throws Exception {

        MemberSignupRequest request = MemberSignupRequest.builder()
                .memberEmail("Jake95@naver.com")
                .memberNickname("J크")
                .memberPassword("qwer1234")
                .memberBirthDate(LocalDateTime.of(1995, 12, 6, 7, 30))
                .memberName("Jake")
                .build();

        given(memberService.signUp(any(MemberSignupRequest.class)))
                .willReturn(MemberSignupResponse.builder()
                        .code(HttpStatus.OK.value())
                        .status(HttpStatus.OK)
                        .message("회원 가입 성공")
                        .build());
        // when
        // then
        mockMvc.perform(
                post("/api/member/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper()
                                .registerModule(new JavaTimeModule())
                                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                                .writeValueAsString(request))
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("signup",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("memberEmail").type(JsonFieldType.STRING)
                                        .description("이메일"),
                                fieldWithPath("memberNickname").type(JsonFieldType.STRING)
                                        .description("닉네임"),
                                fieldWithPath("memberPassword").type(JsonFieldType.STRING)
                                        .description("비밀번호"),
                                fieldWithPath("memberBirthDate").type(JsonFieldType.STRING)
                                        .description("생년월일"),
                                fieldWithPath("memberName").type(JsonFieldType.STRING)
                                        .description("이름")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("응답 코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("응답 상태"),
                                 fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지")

                        )
                ));
        
        verify(memberService).signUp(any(MemberSignupRequest.class));
    }

    @DisplayName("Id, PW를 받아 로그인을 진행한다.")
    @Test
    public void login() throws Exception {

        MemberLoginRequest request = MemberLoginRequest.builder()
                .memberEmail("Dong96@naver.com")
                .memberPassword("qwer1234")
                .build();

        given(memberService.login(any(MemberLoginRequest.class)))
                .willReturn(ApiResponse.of(
                        HttpStatus.OK,
                        "로그인에 성공하셨습니다.",
                        MemberLoginResponse.builder()
                                .memberId(1L)
                                .memberNickname("동동카")
                                .memberToken("somekindoftokenblahblahlol")
                        .build())
                );

        // when
        // then
        mockMvc.perform(
                        get("/api/member/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper()
                                        .registerModule(new JavaTimeModule())
                                        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                                        .writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("login",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("memberEmail").type(JsonFieldType.STRING)
                                        .description("이메일"),
                                fieldWithPath("memberPassword").type(JsonFieldType.STRING)
                                        .description("비밀번호")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("응답 코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("응답 상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.OBJECT)
                                        .description("응답 데이터"),
                                fieldWithPath("data.memberId").type(JsonFieldType.NUMBER)
                                        .description("사용자 Index"),
                                fieldWithPath("data.memberNickname").type(JsonFieldType.STRING)
                                        .description("사용자 닉네임"),
                                fieldWithPath("data.memberToken").type(JsonFieldType.STRING)
                                        .description("사용자 토큰")

                        )
                ));

        verify(memberService).login(any(MemberLoginRequest.class));
    }
}