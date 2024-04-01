package com.ssafy.pennypal.domain.card.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ssafy.pennypal.common.RestDocsSupport;
import com.ssafy.pennypal.domain.card.dto.response.CardResponse;
import com.ssafy.pennypal.domain.card.entity.Card;
import com.ssafy.pennypal.domain.card.service.CardService;
import com.ssafy.pennypal.global.common.api.ApiResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CardControllerTest extends RestDocsSupport {
    private final CardService cardService = mock(CardService.class);

    @Override
    protected Object initController() {
        return new CardController(cardService);
    }

    @DisplayName("모든 카드의 정보를 조회한다.")
    @Test
    public void getCards() throws Exception {
        // given
        List<CardResponse> cards = new ArrayList<>();
        CardResponse cardResponse = CardResponse.builder()
                .cardId(1L)
                .cardType("신용")
                .cardCompany("신한카드")
                .cardName("신한카드 Mr.Life")
                .cardBenefitType("할인형")
                .cardImg("https://api.card-gorilla.com:8080/storage/card/13/card_img/28201/13card.png")
                .cardTopCategory("공과금, 마트/편의점, 푸드")
                .cardCategory("공과금/렌탈, 교통, 기타, 마트/편의점, 병원/약국, 쇼핑, 주유, 통신, 푸드")
                .cardRequired(300000)
                .cardDomestic(0)
                .cardAbroad(15000)
                .build();

        CardResponse cardResponse2 = CardResponse.builder()
                .cardId(2L)
                .cardType("신용")
                .cardCompany("KB국민카드")
                .cardName("KB국민 My WE:SH 카드")
                .cardBenefitType("할인형")
                .cardImg("https://api.card-gorilla.com:8080/storage/card/2441/card_img/28283/2441card.png")
                .cardTopCategory("간편결제, 푸드, 선택형")
                .cardCategory("간편결제, 교통, 기타, 레저/스포츠, 마트/편의점, 뷰티/피트니스, 영화/문화, 카페/디저트, 통신, 푸드")
                .cardRequired(400000)
                .cardDomestic(15000)
                .cardAbroad(15000)
                .build();

        cards.add(cardResponse);
        cards.add(cardResponse2);

        given(cardService.getCards())
                .willReturn(ApiResponse.ok(cards));

        // when
        // then

        mockMvc.perform(
                        get("/api/card")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper()
                                        .registerModule(new JavaTimeModule())
                                        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                                        .writeValueAsString(""))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("getCards",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("응답 코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("응답 상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.ARRAY)
                                        .description("카드 데이터"),
                                fieldWithPath("data[].cardId").type(JsonFieldType.NUMBER)
                                        .description("카드 Id"),
                                fieldWithPath("data[].cardType").type(JsonFieldType.STRING)
                                        .description("체크카드/신용카드 여부"),
                                fieldWithPath("data[].cardCompany").type(JsonFieldType.STRING)
                                        .description("카드 회사명"),
                                fieldWithPath("data[].cardName").type(JsonFieldType.STRING)
                                        .description("카드명"),
                                fieldWithPath("data[].cardBenefitType").type(JsonFieldType.STRING)
                                        .description("카드 보상 타입"),
                                fieldWithPath("data[].cardImg").type(JsonFieldType.STRING)
                                        .description("카드 이미지 url"),
                                fieldWithPath("data[].cardTopCategory").type(JsonFieldType.STRING)
                                        .description("카드 혜택 top3"),
                                fieldWithPath("data[].cardCategory").type(JsonFieldType.STRING)
                                        .description("카드 혜택"),
                                fieldWithPath("data[].cardRequired").type(JsonFieldType.NUMBER)
                                        .description("전월 실적"),
                                fieldWithPath("data[].cardDomestic").type(JsonFieldType.NUMBER)
                                        .description("국내 연회비"),
                                fieldWithPath("data[].cardAbroad").type(JsonFieldType.NUMBER)
                                        .description("해외 연회비")

                                )
                ));

    }


}