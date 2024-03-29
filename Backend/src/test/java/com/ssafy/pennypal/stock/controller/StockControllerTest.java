package com.ssafy.pennypal.stock.controller;

import com.ssafy.pennypal.common.RestDocsSupport;
import com.ssafy.pennypal.stock.service.IStockService;
import lombok.extern.slf4j.Slf4j;

import static org.mockito.Mockito.mock;


@Slf4j
class StockControllerTest extends RestDocsSupport {

    private final IStockService stockService = mock(IStockService.class);


    @Override
    protected Object initController() {
        return new StockController(stockService);
    }


//    @DisplayName("주식 리스트 불러오기")
//    @Test
//    void 주식_리스트_불러오기() throws Exception {
//        // given
//        Pageable pageable = PageRequest.of(0, 5);
//
//        // 테스트 데이터 준비
//        List<StockWithLatestTransactionDto> content = List.of(
//                StockWithLatestTransactionDto.builder()
//                        .stockId(2306L)
//                        .crno("1801110004894")
//                        .isinCd("KR7004110003")
//                        .stckIssuCmpyNm("대선주조")
//                        .basDt(LocalDate.parse("2024-03-28"))
//                        .stckGenrDvdnAmt(939673)
//                        .build(),
//
//                StockWithLatestTransactionDto.builder()
//                        .stockId(2091L)
//                        .crno("1101110216451")
//                        .isinCd("KR7309860005")
//                        .stckIssuCmpyNm("효성투자개발")
//                        .basDt(LocalDate.parse("2024-03-28"))
//                        .stckGenrDvdnAmt(220000)
//                        .build(),
//
//                StockWithLatestTransactionDto.builder()
//                        .stockId(715L)
//                        .crno("1615110021158")
//                        .isinCd("KR7038990008")
//                        .stckIssuCmpyNm("넥스콘테크놀러지")
//                        .basDt(LocalDate.parse("2024-03-28"))
//                        .stckGenrDvdnAmt(139000)
//                        .build(),
//
//                StockWithLatestTransactionDto.builder()
//                        .stockId(1842L)
//                        .crno("1101114884155")
//                        .isinCd("KR715782K024")
//                        .stckIssuCmpyNm("네파")
//                        .basDt(LocalDate.parse("2024-03-28"))
//                        .stckGenrDvdnAmt(54810)
//                        .build(),
//
//                StockWithLatestTransactionDto.builder()
//                        .stockId(2096L)
//                        .crno("2101110134751")
//                        .isinCd("KR7311790000")
//                        .stckIssuCmpyNm("모스터일렉")
//                        .basDt(LocalDate.parse("2024-03-28"))
//                        .stckGenrDvdnAmt(39200)
//                        .build()
//        );
//
//        Page<StockWithLatestTransactionDto> page = new PageImpl<>(content, pageable, content.size());
//
//        given(stockService.getStockList(any(Pageable.class)))
//                .willReturn(page);
//        // when
//        log.info("pageable = {}", pageable);
//        // then
//
//        mockMvc.perform(
//                        get("/api/stock/list?page=2&size=5")
//                                .contentType(MediaType.APPLICATION_JSON)
//                )
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andDo(document("get-stock-list",
//                                preprocessRequest(prettyPrint()),
//                                preprocessResponse(prettyPrint()),
//                                queryParameters(
//                                        parameterWithName("page").description(""),
//                                        parameterWithName("size").description("Entries per page")
//                                ),
//                                responseFields(
//                                        fieldWithPath("code").type(JsonFieldType.NUMBER)
//                                                .description("코드"),
//                                        fieldWithPath("status").type(JsonFieldType.STRING)
//                                                .description("상태"),
//                                        fieldWithPath("message").type(JsonFieldType.STRING)
//                                                .description("메시지"),
//                                        fieldWithPath("data").type(JsonFieldType.OBJECT)
//                                                .description("응답 데이터"),
//                                        fieldWithPath("data.content").type(JsonFieldType.STRING)
//                                                .description("유저 Email"),
//                                        fieldWithPath("data.content[].stockId").type(JsonFieldType.STRING)
//                                                .description("유저 API 키"),
//                                        fieldWithPath("data.content[].crno").type(JsonFieldType.STRING)
//                                                .description("생성 일자"),
//                                        fieldWithPath("data.content[].isinCd").type(JsonFieldType.STRING)
//                                                .description("수정 일자"),
//                                        fieldWithPath("data.content[].stckIssuCmpyNm").type(JsonFieldType.STRING)
//                                                .description("수정 일자"),
//                                        fieldWithPath("data.content[].basDt").type(JsonFieldType.STRING)
//                                                .description("수정 일자"),
//                                        fieldWithPath("data.pageable").type(JsonFieldType.STRING)
//                                                .description("수정 일자"),
//                                        fieldWithPath("data.pageable.pageNumber").type(JsonFieldType.STRING)
//                                                .description("수정 일자"),
//                                        fieldWithPath("data.pageable.pageSize").type(JsonFieldType.STRING)
//                                                .description("수정 일자"),
//                                        fieldWithPath("data.pageable.sort").type(JsonFieldType.STRING)
//                                                .description("수정 일자"),
//                                        fieldWithPath("data.pageable.sort.empty").type(JsonFieldType.STRING)
//                                                .description("수정 일자"),
//                                        fieldWithPath("data.pageable.sort.sorted").type(JsonFieldType.STRING)
//                                                .description("수정 일자"),
//                                        fieldWithPath("data.pageable.sort.unsorted").type(JsonFieldType.STRING)
//                                                .description("수정 일자")
//                                )
//                        )
//                );
//    }
}