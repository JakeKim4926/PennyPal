package com.ssafy.pennypal.stock.controller;

import com.ssafy.pennypal.common.RestDocsSupport;
import com.ssafy.pennypal.stock.dto.response.StockWithLatestTransactionDto;
import com.ssafy.pennypal.stock.service.IStockService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
class StockControllerTest extends RestDocsSupport {

    private final IStockService stockService = mock(IStockService.class);


    @Override
    protected Object initController() {
        return new StockController(stockService);
    }


    @DisplayName("주식 리스트 불러오기")
    @Test
    void 주식_리스트_불러오기() throws Exception {
        // given
        Pageable pageable = PageRequest.of(0, 5);

        // 테스트 데이터 준비
        List<StockWithLatestTransactionDto> content = List.of(
                StockWithLatestTransactionDto.builder()
                        .stockId(2306L)
                        .crno("1801110004894")
                        .isinCd("KR7004110003")
                        .stckIssuCmpyNm("대선주조")
                        .basDt(LocalDate.parse("2024-03-28"))
                        .stckGenrDvdnAmt(939673)
                        .build(),

                StockWithLatestTransactionDto.builder()
                        .stockId(2091L)
                        .crno("1101110216451")
                        .isinCd("KR7309860005")
                        .stckIssuCmpyNm("효성투자개발")
                        .basDt(LocalDate.parse("2024-03-28"))
                        .stckGenrDvdnAmt(220000)
                        .build(),

                StockWithLatestTransactionDto.builder()
                        .stockId(715L)
                        .crno("1615110021158")
                        .isinCd("KR7038990008")
                        .stckIssuCmpyNm("넥스콘테크놀러지")
                        .basDt(LocalDate.parse("2024-03-28"))
                        .stckGenrDvdnAmt(139000)
                        .build(),

                StockWithLatestTransactionDto.builder()
                        .stockId(1842L)
                        .crno("1101114884155")
                        .isinCd("KR715782K024")
                        .stckIssuCmpyNm("네파")
                        .basDt(LocalDate.parse("2024-03-28"))
                        .stckGenrDvdnAmt(54810)
                        .build(),

                StockWithLatestTransactionDto.builder()
                        .stockId(2096L)
                        .crno("2101110134751")
                        .isinCd("KR7311790000")
                        .stckIssuCmpyNm("모스터일렉")
                        .basDt(LocalDate.parse("2024-03-28"))
                        .stckGenrDvdnAmt(39200)
                        .build()
        );

        Page<StockWithLatestTransactionDto> page = new PageImpl<>(content, pageable, content.size());

        given(stockService.getStockList(any(Pageable.class)))
                .willReturn(page);
        // when
        log.info("pageable = {}", pageable);
        // then

        mockMvc.perform(
                        get("/api/stock/list")
                                .param("page", "0")
                                .param("size", "5")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-stock-list",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                queryParameters(
                                        parameterWithName("page").description("페이지 넘버 요청")
                                                .optional(),
                                        parameterWithName("size").description("페이지 크기 요청")
                                                .optional()
                                ),
                                responseFields(
                                        fieldWithPath("code").type(JsonFieldType.NUMBER)
                                                .description("코드"),
                                        fieldWithPath("status").type(JsonFieldType.STRING)
                                                .description("상태"),
                                        fieldWithPath("message").type(JsonFieldType.STRING)
                                                .description("메시지"),
                                        fieldWithPath("data").type(JsonFieldType.OBJECT)
                                                .description("응답 데이터"),
                                        fieldWithPath("data.content").type(JsonFieldType.ARRAY)
                                                .description("응답 데이터 배열"),
                                        fieldWithPath("data.content[].stockId").type(JsonFieldType.NUMBER)
                                                .description("응답 데이터 주식 ID"),
                                        fieldWithPath("data.content[].crno").type(JsonFieldType.STRING)
                                                .description("응답 데이터 주식 CRNO"),
                                        fieldWithPath("data.content[].isinCd").type(JsonFieldType.STRING)
                                                .description("응답 데이터 주식 ISIN"),
                                        fieldWithPath("data.content[].stckIssuCmpyNm").type(JsonFieldType.STRING)
                                                .description("응답 데이터 주식 발행 회사 명"),
                                        fieldWithPath("data.content[].basDt").type(JsonFieldType.ARRAY)
                                                .description("응답 데이터 주식 배당금 지급 날짜"),
                                        fieldWithPath("data.content[].stckGenrDvdnAmt").type(JsonFieldType.NUMBER)
                                                .description("응답 데이터 주식 배당금 금액"),
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
                        )
                );
    }
}