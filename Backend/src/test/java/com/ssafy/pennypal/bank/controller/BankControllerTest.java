package com.ssafy.pennypal.bank.controller;


import com.ssafy.pennypal.bank.dto.service.request.UserAccountRequestServiceDTO;
import com.ssafy.pennypal.bank.dto.service.response.UserAccountResponseServiceDTO;
import com.ssafy.pennypal.bank.dto.service.response.UserAccountResponseServicePayLoadDTO;
import com.ssafy.pennypal.bank.service.api.IBankServiceAPI;
import com.ssafy.pennypal.bank.service.db.IBankServiceDB;
import com.ssafy.pennypal.common.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BankControllerTest extends RestDocsSupport {

    private final IBankServiceAPI bankServiceAPI = mock(IBankServiceAPI.class);
    private final IBankServiceDB bankServiceDB = mock(IBankServiceDB.class);

    @Override
    protected Object initController() {
        return new BankController(bankServiceAPI, bankServiceDB);
    }

    @DisplayName("사용자 계정 생성")
    @Test
    void 사용자_계정_생성() throws Exception {
        // given
        UserAccountRequestServiceDTO userAccountRequestServiceDTO = UserAccountRequestServiceDTO.builder()
                .apiKey("82d37624494f4092bf96d5f4dbb634c4")
                .userEmail("mine702@naver.com")
                .build();

        given(bankServiceAPI.createUserAccount(any(UserAccountRequestServiceDTO.class)))
                .willReturn(UserAccountResponseServiceDTO.builder()
                        .code("succeed")
                        .payload(
                                UserAccountResponseServicePayLoadDTO.builder()
                                        .userEmail("mine702@naver.com")
                                        .userName("mine702")
                                        .institutionCode("001")
                                        .userKey("13cefdcf-494f-4092-bf96-d5f4dbb634c4")
                                        .created("2024-03-04T12:41:30.921299+09:00")
                                        .modified("2024-03-04T12:41:30.921299+09:00")
                                        .build()
                        )
                        .now("2024-03-18T15:35:34.504746+09:00")
                        .build());
        // when
        // then
        mockMvc.perform(
                        post("/bank/api/user/api/key/{userEmail}", userAccountRequestServiceDTO.getUserEmail())
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("create-account",
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
                                        fieldWithPath("data.userEmail").type(JsonFieldType.STRING)
                                                .description("유저 Email"),
                                        fieldWithPath("data.userKey").type(JsonFieldType.STRING)
                                                .description("유저 API 키"),
                                        fieldWithPath("data.created").type(JsonFieldType.STRING)
                                                .description("생성 일자"),
                                        fieldWithPath("data.modified").type(JsonFieldType.STRING)
                                                .description("수정 일자")
                                )
                        )
                );

    }

    @DisplayName("사용자 계정 조회")
    @Test
    void 사용자_계정_조회() throws Exception {
        // given
        UserAccountRequestServiceDTO userAccountRequestServiceDTO = UserAccountRequestServiceDTO.builder()
                .apiKey("82d37624494f4092bf96d5f4dbb634c4")
                .userEmail("mine702@naver.com")
                .build();

        given(bankServiceAPI.getUserAccount(any(UserAccountRequestServiceDTO.class)))
                .willReturn(UserAccountResponseServiceDTO.builder()
                        .code("succeed")
                        .payload(
                                UserAccountResponseServicePayLoadDTO.builder()
                                        .userEmail("mine702@naver.com")
                                        .userName("mine702")
                                        .institutionCode("001")
                                        .userKey("13cefdcf-494f-4092-bf96-d5f4dbb634c4")
                                        .created("2024-03-04T12:41:30.921299+09:00")
                                        .modified("2024-03-04T12:41:30.921299+09:00")
                                        .build()
                        )
                        .now("2024-03-18T15:35:34.504746+09:00")
                        .build());
        // when
        // then
        mockMvc.perform(
                        get("/bank/api/user/api/key/{userEmail}", userAccountRequestServiceDTO.getUserEmail())
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-account",
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
                                        fieldWithPath("data.userEmail").type(JsonFieldType.STRING)
                                                .description("유저 Email"),
                                        fieldWithPath("data.userKey").type(JsonFieldType.STRING)
                                                .description("유저 API 키"),
                                        fieldWithPath("data.created").type(JsonFieldType.STRING)
                                                .description("생성 일자"),
                                        fieldWithPath("data.modified").type(JsonFieldType.STRING)
                                                .description("수정 일자")
                                )
                        )
                );

    }

    @DisplayName("사용자 계좌 조회")
    @Test
    void 사용자_계좌_조회() {
        // given

        // when

        // then
    }

}