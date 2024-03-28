package com.ssafy.pennypal.domain.member.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ssafy.pennypal.common.RestDocsSupport;
import com.ssafy.pennypal.domain.member.dto.request.*;
import com.ssafy.pennypal.domain.member.dto.response.MemberLoginResponse;
import com.ssafy.pennypal.domain.member.dto.response.MemberSignupResponse;
import com.ssafy.pennypal.domain.member.entity.Member;
import com.ssafy.pennypal.domain.member.service.AttendService;
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
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



class MemberControllerTest extends RestDocsSupport {

    private final MemberService memberService = mock(MemberService.class);
    private final AttendService attendService = mock(AttendService.class);

    @Override
    protected Object initController() {
        return new MemberController(memberService, attendService);
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

    @DisplayName("memberId, 닉네임을 받아 사용자의 닉네임을 수정한다.")
    @Test
    public void updateNickname() throws Exception {

        MemberUpdateNicknameRequest request = MemberUpdateNicknameRequest.builder()
                .memberId(1L)
                .memberNickname("옹옹씌")
                .build();

        given(memberService.updateNickname(any(MemberUpdateNicknameRequest.class)))
                .willReturn(ApiResponse.of(
                        HttpStatus.OK,
                        "닉네임 변경에 성공하셨습니다.",
                        request.getMemberNickname())
                );

        // when
        // then
        mockMvc.perform(
                        patch("/api/member/nickname")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper()
                                        .registerModule(new JavaTimeModule())
                                        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                                        .writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("update-nickname",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("memberId").type(JsonFieldType.NUMBER)
                                        .description("사용자 Index"),
                                fieldWithPath("memberNickname").type(JsonFieldType.STRING)
                                        .description("수정할 닉네임")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("응답 코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("응답 상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.STRING)
                                        .description("응답 데이터")
                        )
                ));

        verify(memberService).updateNickname(any(MemberUpdateNicknameRequest.class));
    }

    @DisplayName("memberId, 비밀번호, 수정할 비밀번호를 받아 비밀번호를 수정한다.")
    @Test
    public void updatePassword() throws Exception {

        MemberUpdatePasswordRequest request = MemberUpdatePasswordRequest.builder()
                .memberId(1L)
                .memberOriginPassword("gorila97")
                .memberChangePassword("97gorila")
                .build();

        given(memberService.updatePassword(any(MemberUpdatePasswordRequest.class)))
                .willReturn(ApiResponse.of(
                        HttpStatus.OK,
                        "비밀번호 변경에 성공하셨습니다."
                        ,""
                ));

        // when
        // then
        mockMvc.perform(
                        patch("/api/member/password")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper()
                                        .registerModule(new JavaTimeModule())
                                        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                                        .writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("update-password",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("memberId").type(JsonFieldType.NUMBER)
                                        .description("사용자 Index"),
                                fieldWithPath("memberOriginPassword").type(JsonFieldType.STRING)
                                        .description("사용자 비밀번호"),
                                fieldWithPath("memberChangePassword").type(JsonFieldType.STRING)
                                        .description("수정할 비밀번호")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("응답 코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("응답 상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.STRING)
                                        .description("응답 데이터")
                        )
                ));

        verify(memberService).updatePassword(any(MemberUpdatePasswordRequest.class));
    }

    @DisplayName("출석 요청을 받아 출석을 진행한다.")
    @Test
    public void attend() throws Exception {

        MemberAttendRequest request = MemberAttendRequest.builder()
                .memberId(1L)
                .memberDate(LocalDateTime.now())
                .build();

        given(attendService.attend(any(MemberAttendRequest.class)))
                .willReturn(ApiResponse.of(
                        HttpStatus.OK,
                        "출석 인증에 성공하셨습니다."
                        ,""
                ));

        // when
        // then
        mockMvc.perform(
                        get("/api/member/attend")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper()
                                        .registerModule(new JavaTimeModule())
                                        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                                        .writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("attend",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("memberId").type(JsonFieldType.NUMBER)
                                        .description("사용자 Index"),
                                fieldWithPath("memberDate").type(JsonFieldType.STRING)
                                        .description("현재 날짜")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("응답 코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("응답 상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.STRING)
                                        .description("응답 데이터")
                        )
                ));

        verify(attendService).attend(any(MemberAttendRequest.class));
    }

    @DisplayName("유저의 index를 받아 출석 여부를 반환한다.")
    @Test
    public void isAttended() throws Exception {

        Long request = 1L;

        given(attendService.getIsAttended(any(Long.class)))
                .willReturn(ApiResponse.of(
                        HttpStatus.OK,
                        "금일 출석을 진행하지 않으셨습니다."
                        ,false
                ));

        // when
        // then
        mockMvc.perform(
                        get("/api/member/attend/state")
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("memberId", request.toString())
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("isAttended",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        queryParameters( // Note the change here to document the request parameter
                                parameterWithName("memberId").description("사용자 Index")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("응답 코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("응답 상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.BOOLEAN)
                                        .description("응답 데이터")
                        )
                ));

        verify(attendService).getIsAttended(any(Long.class));
    }
}