package com.ssafy.pennypal.domain.card.controller;

import com.ssafy.pennypal.common.RestDocsSupport;
import com.ssafy.pennypal.domain.card.dto.request.SearchCard;
import com.ssafy.pennypal.domain.card.dto.response.CardResponse;
import com.ssafy.pennypal.domain.card.service.CardService;
import com.ssafy.pennypal.global.common.api.ApiResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
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
        Pageable pageable = PageRequest.of(0, 4);
        List<CardResponse> cardResponses = List.of(
                CardResponse.builder()
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
                        .build(),

                CardResponse.builder()
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
                        .build()
        );

        Page<CardResponse> page = new PageImpl<>(cardResponses, pageable, cardResponses.size());

        given(cardService.getCards(any(Pageable.class), any(SearchCard.class)))
                .willReturn(ApiResponse.ok(page));

        // when
        // then

        mockMvc.perform(
                        get("/api/card")
                                .param("page", "0")
                                .param("size", "4")
                                .param("cardName", "신한")
                                .param("cardCompany", "")
                                .param("startCardRequired", "")
                                .param("endCardRequired", "")
                                .param("startCardDomestic", "")
                                .param("endCardDomestic", "")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("getCards",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        queryParameters(
                                parameterWithName("page").description("페이지 넘버 요청")
                                        .optional(),
                                parameterWithName("size").description("페이지 크기 요청")
                                        .optional(),
                                parameterWithName("cardName").description("검색 카드 이름")
                                        .optional(),
                                parameterWithName("cardCompany").description("검색 카드 회사")
                                        .optional(),
                                parameterWithName("startCardRequired").description("검색 카드 전월 실적 시작 금액")
                                        .optional(),
                                parameterWithName("endCardRequired").description("검색 카드 전월 실적 종료 금액")
                                        .optional(),
                                parameterWithName("startCardDomestic").description("검색 카드 연회비 시작 금액")
                                        .optional(),
                                parameterWithName("endCardDomestic").description("검색 카드 연회비 종료 금액")
                                        .optional()
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
                                fieldWithPath("data.content").type(JsonFieldType.ARRAY)
                                        .description("응답 데이터 배열"),

                                fieldWithPath("data.content[].cardId").type(JsonFieldType.NUMBER)
                                        .description("카드 Id"),
                                fieldWithPath("data.content[].cardType").type(JsonFieldType.STRING)
                                        .description("체크카드/신용카드 여부"),
                                fieldWithPath("data.content[].cardCompany").type(JsonFieldType.STRING)
                                        .description("카드 회사명"),
                                fieldWithPath("data.content[].cardName").type(JsonFieldType.STRING)
                                        .description("카드명"),
                                fieldWithPath("data.content[].cardBenefitType").type(JsonFieldType.STRING)
                                        .description("카드 보상 타입"),
                                fieldWithPath("data.content[].cardImg").type(JsonFieldType.STRING)
                                        .description("카드 이미지 url"),
                                fieldWithPath("data.content[].cardTopCategory").type(JsonFieldType.STRING)
                                        .description("카드 혜택 top3"),
                                fieldWithPath("data.content[].cardCategory").type(JsonFieldType.STRING)
                                        .description("카드 혜택"),
                                fieldWithPath("data.content[].cardRequired").type(JsonFieldType.NUMBER)
                                        .description("전월 실적"),
                                fieldWithPath("data.content[].cardDomestic").type(JsonFieldType.NUMBER)
                                        .description("국내 연회비"),
                                fieldWithPath("data.content[].cardAbroad").type(JsonFieldType.NUMBER)
                                        .description("해외 연회비"),

                                fieldWithPath("data.pageable").type(JsonFieldType.OBJECT)
                                        .description("응답 데이터 페이지 정보"),
                                fieldWithPath("data.pageable.pageNumber").type(JsonFieldType.NUMBER)
                                        .description("응답 데이터 페이지 현재 페이지 넘버"),
                                fieldWithPath("data.pageable.pageSize").type(JsonFieldType.NUMBER)
                                        .description("응답 데이터 페이지 현재 페이지 크기"),
                                fieldWithPath("data.pageable.sort").type(JsonFieldType.OBJECT)
                                        .description("응답 데이터 페이지 현재 페이지 정렬"),
                                fieldWithPath("data.pageable.sort.empty").type(JsonFieldType.BOOLEAN)
                                        .description("응답 데이터 페이지 현재 페이지 정렬 데이터"),
                                fieldWithPath("data.pageable.sort.unsorted").type(JsonFieldType.BOOLEAN)
                                        .description("응답 데이터 페이지 현재 페이지 정렬 데이터"),
                                fieldWithPath("data.pageable.sort.sorted").type(JsonFieldType.BOOLEAN)
                                        .description("응답 데이터 페이지 현재 페이지 정렬 데이터"),
                                fieldWithPath("data.pageable.offset").type(JsonFieldType.NUMBER)
                                        .description("응답 데이터 페이지 offset"),
                                fieldWithPath("data.pageable.paged").type(JsonFieldType.BOOLEAN)
                                        .description("응답 데이터 페이지 paged"),
                                fieldWithPath("data.pageable.unpaged").type(JsonFieldType.BOOLEAN)
                                        .description("응답 데이터 페이지 unpaged"),
                                fieldWithPath("data.last").type(JsonFieldType.BOOLEAN)
                                        .description("응답 데이터 마지막 페이지 여부"),
                                fieldWithPath("data.totalElements").type(JsonFieldType.NUMBER)
                                        .description("응답 데이터 총 숫자"),
                                fieldWithPath("data.totalPages").type(JsonFieldType.NUMBER)
                                        .description("응답 데이터 총 페이지"),
                                fieldWithPath("data.first").type(JsonFieldType.BOOLEAN)
                                        .description("응답 데이터 첫페이지 여부"),
                                fieldWithPath("data.numberOfElements").type(JsonFieldType.NUMBER)
                                        .description("응답 데이터 크기"),
                                fieldWithPath("data.size").type(JsonFieldType.NUMBER)
                                        .description("응답 데이터 크기"),
                                fieldWithPath("data.number").type(JsonFieldType.NUMBER)
                                        .description("응답 데이터 페이지 넘버"),
                                fieldWithPath("data.sort").type(JsonFieldType.OBJECT)
                                        .description("응답 데이터 정렬"),
                                fieldWithPath("data.sort.empty").type(JsonFieldType.BOOLEAN)
                                        .description("응답 데이터 정렬 empty"),
                                fieldWithPath("data.sort.unsorted").type(JsonFieldType.BOOLEAN)
                                        .description("응답 데이터 정렬 unsorted"),
                                fieldWithPath("data.sort.sorted").type(JsonFieldType.BOOLEAN)
                                        .description("응답 데이터 정렬 sorted"),
                                fieldWithPath("data.empty").type(JsonFieldType.BOOLEAN)
                                        .description("응답 데이터 empty")

                        )
                ));

    }


}