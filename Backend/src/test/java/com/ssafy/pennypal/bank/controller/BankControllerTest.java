package com.ssafy.pennypal.bank.controller;


import com.ssafy.pennypal.bank.dto.controller.request.AccountDrawingTransferControllerDTO;
import com.ssafy.pennypal.bank.dto.controller.request.AccountTransactionRequestDTO;
import com.ssafy.pennypal.bank.dto.controller.request.AccountTransferRequestDTO;
import com.ssafy.pennypal.bank.dto.service.common.CommonHeaderResponseDTO;
import com.ssafy.pennypal.bank.dto.service.request.AccountTransactionRequestServiceDTO;
import com.ssafy.pennypal.bank.dto.service.request.GetUserAccountListServiceRequestDTO;
import com.ssafy.pennypal.bank.dto.service.request.UserAccountRequestServiceDTO;
import com.ssafy.pennypal.bank.dto.service.request.UserBankAccountRequestServiceDTO;
import com.ssafy.pennypal.bank.dto.service.response.*;
import com.ssafy.pennypal.bank.service.api.IBankServiceAPI;
import com.ssafy.pennypal.bank.service.db.IBankServiceDB;
import com.ssafy.pennypal.common.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.List;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
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
                .userId("mine702@naver.com")
                .build();

        given(bankServiceAPI.createUserAccount(any(UserAccountRequestServiceDTO.class)))
                .willReturn(UserAccountResponseServiceDTO.builder()
                        .code("succeed")
                        .payload(
                                UserAccountResponseServicePayLoadDTO.builder()
                                        .userId("mine702@naver.com")
                                        .userName("mine702")
                                        .institutionCode("001")
                                        .userKey("13cefdcf-494f-4092-bf96-d5f4dbb634c4")
                                        .created("2024-03-04T12:41:30.921299+09:00")
                                        .modified("2024-03-04T12:41:30.921299+09:00")
                                        .build()
                        )
                        .now("2024-03-18T15:35:34.504746+09:00")
                        .build());

        given(bankServiceAPI.createUserBankAccount(any(UserBankAccountRequestServiceDTO.class)))
                .willReturn(UserBankAccountResponseServiceDTO.builder()
                        .Header(
                                CommonHeaderResponseDTO.builder()
                                        .responseCode("H0000")
                                        .responseMessage("정상처리 되었습니다.")
                                        .apiName("openAccount")
                                        .transmissionDate("20240101")
                                        .transmissionTime("121212")
                                        .institutionCode("00100")
                                        .apiKey("82d37624494f4092bf96d5f4dbb634c4")
                                        .apiServiceCode("openAccount")
                                        .institutionTransactionUniqueNo("20240215121212123452")
                                        .build()
                        )
                        .REC(
                                UserBankAccountResponseRECServiceDTO.builder()
                                        .bankCode("001")
                                        .accountNo("0016826085496269")
                                        .build()
                        )
                        .build());

        // when
        // then
        mockMvc.perform(
                        post("/api/bank/user/key/{userEmail}", userAccountRequestServiceDTO.getUserId())
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
                .userId("mine702@naver.com")
                .build();

        given(bankServiceAPI.getUserAccount(any(UserAccountRequestServiceDTO.class)))
                .willReturn(UserAccountResponseServiceDTO.builder()
                        .code("succeed")
                        .payload(
                                UserAccountResponseServicePayLoadDTO.builder()
                                        .userId("mine702@naver.com")
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
                        get("/api/bank/user/key/{userEmail}", userAccountRequestServiceDTO.getUserId())
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
    void 사용자_계좌_조회() throws Exception {
        // given
        UserAccountRequestServiceDTO userAccountRequestServiceDTO = UserAccountRequestServiceDTO.builder()
                .apiKey("82d37624494f4092bf96d5f4dbb634c4")
                .userId("mine702@naver.com")
                .build();

        given(bankServiceAPI.getUserAccount(any(UserAccountRequestServiceDTO.class)))
                .willReturn(UserAccountResponseServiceDTO.builder()
                        .code("succeed")
                        .payload(
                                UserAccountResponseServicePayLoadDTO.builder()
                                        .userId("mine702@naver.com")
                                        .userName("mine702")
                                        .institutionCode("001")
                                        .userKey("13cefdcf-494f-4092-bf96-d5f4dbb634c4")
                                        .created("2024-03-04T12:41:30.921299+09:00")
                                        .modified("2024-03-04T12:41:30.921299+09:00")
                                        .build()
                        )
                        .now("2024-03-18T15:35:34.504746+09:00")
                        .build());

        given(bankServiceAPI.getUserAccountList(any(GetUserAccountListServiceRequestDTO.class)))
                .willReturn(UserBankAccountsResponseServiceDTO.builder()
                        .header(
                                CommonHeaderResponseDTO.builder()
                                        .responseCode("H0000")
                                        .responseMessage("정상처리 되었습니다.")
                                        .apiName("inquireAccountList")
                                        .transmissionDate("20240101")
                                        .transmissionTime("121212")
                                        .institutionCode("00100")
                                        .apiKey("82d37624494f4092bf96d5f4dbb634c4")
                                        .apiServiceCode("inquireAccountList")
                                        .institutionTransactionUniqueNo("20240215121212123468")
                                        .build()
                        )
                        .REC(
                                List.of(
                                        UserAccountListResponseServiceDTO.builder()
                                                .bankCode("001")
                                                .bankName("한국은행")
                                                .userName("test")
                                                .accountNo("0016826085496269")
                                                .accountName("한국은행 수시입출금")
                                                .accountTypeCode("1")
                                                .accountTypeName("수시입출금")
                                                .accountCreatedDate("20240304")
                                                .accountExpiryDate("20290304")
                                                .dailyTransferLimit("1000000000000")
                                                .oneTimeTransferLimit("1000000000000")
                                                .accountBalance("0")
                                                .build(),

                                        UserAccountListResponseServiceDTO.builder()
                                                .bankCode("002")
                                                .bankName("산업은행")
                                                .userName("test")
                                                .accountNo("0028889135848149")
                                                .accountName("산업은행 수시입출금")
                                                .accountTypeCode("1")
                                                .accountTypeName("수시입출금")
                                                .accountCreatedDate("20240304")
                                                .accountExpiryDate("20290304")
                                                .dailyTransferLimit("1000000000000")
                                                .oneTimeTransferLimit("1000000000000")
                                                .accountBalance("0")
                                                .build()
                                )
                        )
                        .build());
        // when
        // then
        mockMvc.perform(
                        get("/api/bank/user/account/{userEmail}", userAccountRequestServiceDTO.getUserId())
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-bank-account",
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
                                        fieldWithPath("data.header").type(JsonFieldType.OBJECT)
                                                .description("응답 데이터 헤더"),
                                        fieldWithPath("data.header.responseCode").type(JsonFieldType.STRING)
                                                .description("응답 코드"),
                                        fieldWithPath("data.header.responseMessage").type(JsonFieldType.STRING)
                                                .description("응답 메시지"),
                                        fieldWithPath("data.rec").type(JsonFieldType.ARRAY)
                                                .description("응답 데이터 REC"),
                                        fieldWithPath("data.rec[].bankCode").type(JsonFieldType.STRING)
                                                .description("응답 데이터 은행 코드"),
                                        fieldWithPath("data.rec[].bankName").type(JsonFieldType.STRING)
                                                .description("응답 데이터 은행 이름"),
                                        fieldWithPath("data.rec[].accountNo").type(JsonFieldType.STRING)
                                                .description("응답 데이터 계좌 번호")
                                )
                        )
                );
    }

    @DisplayName("사용자 계좌 거래 내역 조회")
    @Test
    void 사용자_계좌_거래내역_조회() throws Exception {

        // given
        AccountTransactionRequestDTO requestDTO = AccountTransactionRequestDTO.builder()
                .userEmail("mine702@naver.com")
                .bankCode("001")
                .transactionType("A")
                .accountNo("0014375203694183")
                .startDate("20240101")
                .endDate("20241231")
                .build();

        given(bankServiceAPI.getUserAccount(any(UserAccountRequestServiceDTO.class)))
                .willReturn(UserAccountResponseServiceDTO.builder()
                        .code("succeed")
                        .payload(
                                UserAccountResponseServicePayLoadDTO.builder()
                                        .userId("mine702@naver.com")
                                        .userName("mine702")
                                        .institutionCode("001")
                                        .userKey("13cefdcf-494f-4092-bf96-d5f4dbb634c4")
                                        .created("2024-03-04T12:41:30.921299+09:00")
                                        .modified("2024-03-04T12:41:30.921299+09:00")
                                        .build()
                        )
                        .now("2024-03-18T15:35:34.504746+09:00")
                        .build());

        given(bankServiceAPI.getUserAccountTransaction(any(AccountTransactionRequestServiceDTO.class)))
                .willReturn(AccountTransactionListResponseServiceDTO.builder()
                        .header(
                                CommonHeaderResponseDTO.builder()
                                        .responseCode("H0000")
                                        .responseMessage("정상처리 되었습니다.")
                                        .apiName("inquireAccountTransactionHistory")
                                        .transmissionDate("20240101")
                                        .transmissionTime("121212")
                                        .institutionCode("00100")
                                        .apiKey("82d37624494f4092bf96d5f4dbb634c4")
                                        .apiServiceCode("inquireAccountTransactionHistory")
                                        .institutionTransactionUniqueNo("20240215121212123454")
                                        .build()
                        )
                        .REC(
                                AccountTransactionRecResponseDTO.builder()
                                        .totalCount("3")
                                        .list(
                                                List.of(
                                                        AccountTransactionResponseServiceDTO.builder()
                                                                .transactionUniqueNo("107")
                                                                .transactionDate("20240304")
                                                                .transactionTime("130324")
                                                                .transactionType("1")
                                                                .transactionTypeName("입금")
                                                                .transactionBalance("10000000")
                                                                .transactionAfterBalance("10000000")
                                                                .transactionSummary("입금합니다")
                                                                .build(),
                                                        AccountTransactionResponseServiceDTO.builder()
                                                                .transactionUniqueNo("108")
                                                                .transactionDate("20240304")
                                                                .transactionTime("130435")
                                                                .transactionType("2")
                                                                .transactionTypeName("출금")
                                                                .transactionBalance("1000000")
                                                                .transactionAfterBalance("9000000")
                                                                .transactionSummary("출금합니다")
                                                                .build(),
                                                        AccountTransactionResponseServiceDTO.builder()
                                                                .transactionUniqueNo("109")
                                                                .transactionDate("20240304")
                                                                .transactionTime("130640")
                                                                .transactionType("2")
                                                                .transactionTypeName("출금(이체)")
                                                                .transactionAccountNo("0028889135848149")
                                                                .transactionBalance("1000000")
                                                                .transactionAfterBalance("8000000")
                                                                .transactionSummary("출금이체 계좌")
                                                                .build()
                                                )
                                        )
                                        .build()
                        )
                        .build());
        // when
        // then
        mockMvc.perform(
                        get("/api/bank/user/account/transaction")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(requestDTO))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-bank-account-transaction",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestFields(
                                        fieldWithPath("userEmail").type(JsonFieldType.STRING)
                                                .description("사용자 이메일"),
                                        fieldWithPath("bankCode").type(JsonFieldType.STRING)
                                                .description("조회 하고 싶은 은행 코드 번호"),
                                        fieldWithPath("transactionType").type(JsonFieldType.STRING)
                                                .description("거래 내역 조회 정보"),
                                        fieldWithPath("accountNo").type(JsonFieldType.STRING)
                                                .description("거래 내역 조회 계좌 번호"),
                                        fieldWithPath("startDate").type(JsonFieldType.STRING)
                                                .description("시작 날짜"),
                                        fieldWithPath("endDate").type(JsonFieldType.STRING)
                                                .description("종료 날짜")
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
                                        fieldWithPath("data.header").type(JsonFieldType.OBJECT)
                                                .description("응답 데이터 헤더"),
                                        fieldWithPath("data.header.responseCode").type(JsonFieldType.STRING)
                                                .description("응답 코드"),
                                        fieldWithPath("data.header.responseMessage").type(JsonFieldType.STRING)
                                                .description("응답 메시지"),
                                        fieldWithPath("data.rec").type(JsonFieldType.ARRAY)
                                                .description("응답 데이터 REC"),
                                        fieldWithPath("data.rec[].transactionUniqueNo").type(JsonFieldType.STRING)
                                                .description("응답 데이터 계좌 내역 유일번호"),
                                        fieldWithPath("data.rec[].transactionDate").type(JsonFieldType.STRING)
                                                .description("응답 데이터 계좌 내역 날짜"),
                                        fieldWithPath("data.rec[].transactionType").type(JsonFieldType.STRING)
                                                .description("응답 데이터 계좌 내역 입 출금 조회"),
                                        fieldWithPath("data.rec[].transactionBalance").type(JsonFieldType.STRING)
                                                .description("응답 데이터 계좌 내역 금액"),
                                        fieldWithPath("data.rec[].transactionSummary").type(JsonFieldType.STRING)
                                                .description("응답 데이터 계좌 내역 문구"),
                                        fieldWithPath("data.rec[].transactionAccountNo").type(JsonFieldType.STRING)
                                                .description("응답 데이터 출금 이체 계좌 번호")
                                                .optional()
                                )
                        )
                );
    }

    @DisplayName("주식 계좌 생성")
    @Test
    void 주식_계좌_생성() throws Exception {
        // given
        UserAccountRequestServiceDTO userAccountRequestServiceDTO = UserAccountRequestServiceDTO.builder()
                .apiKey("82d37624494f4092bf96d5f4dbb634c4")
                .userId("mine702@naver.com")
                .build();

        given(bankServiceAPI.getUserAccount(any(UserAccountRequestServiceDTO.class)))
                .willReturn(UserAccountResponseServiceDTO.builder()
                        .code("succeed")
                        .payload(
                                UserAccountResponseServicePayLoadDTO.builder()
                                        .userId("mine702@naver.com")
                                        .userName("mine702")
                                        .institutionCode("001")
                                        .userKey("13cefdcf-494f-4092-bf96-d5f4dbb634c4")
                                        .created("2024-03-04T12:41:30.921299+09:00")
                                        .modified("2024-03-04T12:41:30.921299+09:00")
                                        .build()
                        )
                        .now("2024-03-18T15:35:34.504746+09:00")
                        .build());

        given(bankServiceAPI.createUserBankAccount(any(UserBankAccountRequestServiceDTO.class)))
                .willReturn(
                        UserBankAccountResponseServiceDTO.builder()
                                .Header(
                                        CommonHeaderResponseDTO.builder()
                                                .responseCode("H0000")
                                                .responseMessage("정상처리 되었습니다.")
                                                .apiName("openAccount")
                                                .transmissionDate("20240101")
                                                .transmissionTime("121212")
                                                .institutionCode("00100")
                                                .apiKey("82d37624494f4092bf96d5f4dbb634c4")
                                                .apiServiceCode("openAccount")
                                                .institutionTransactionUniqueNo("20240215121212123454")
                                                .build()
                                )
                                .REC(
                                        UserBankAccountResponseRECServiceDTO.builder()
                                                .bankCode("004")
                                                .accountNo("0016826085496269")
                                                .build()
                                )
                                .build());
        // when
        mockMvc.perform(
                        post("/api/bank/user/stock/account/{userEmail}", userAccountRequestServiceDTO.getUserId())
                                .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-bank-stock-account",
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
                                        fieldWithPath("data.header").type(JsonFieldType.OBJECT)
                                                .description("응답 데이터 헤더"),
                                        fieldWithPath("data.header.responseCode").type(JsonFieldType.STRING)
                                                .description("응답 코드"),
                                        fieldWithPath("data.header.responseMessage").type(JsonFieldType.STRING)
                                                .description("응답 메시지"),
                                        fieldWithPath("data.rec").type(JsonFieldType.OBJECT)
                                                .description("응답 데이터 REC"),
                                        fieldWithPath("data.rec.bankCode").type(JsonFieldType.STRING)
                                                .description("응답 데이터 계좌 은행 코드"),
                                        fieldWithPath("data.rec.accountNo").type(JsonFieldType.STRING)
                                                .description("응답 데이터 계좌 번호")
                                )
                        )
                );
        // then
    }

    @DisplayName("사용자 계좌 이체")
    @Test
    void 사용자_계좌_이체() throws Exception {
        // given
        AccountTransferRequestDTO accountTransferRequestDTO = AccountTransferRequestDTO.builder()
                .userEmail("mine702@naver.com")
                .depositBankCode("004")
                .depositAccountNo("0028889135848149")
                .depositTransactionSummary("입금이체 계좌")
                .transactionBalance("10000")
                .withdrawalBankCode("001")
                .withdrawalAccountNo("0016826085496269")
                .withdrawalTransactionSummary("출금이체 계좌")
                .build();

        given(bankServiceAPI.getUserAccount(any(UserAccountRequestServiceDTO.class)))
                .willReturn(UserAccountResponseServiceDTO.builder()
                        .code("succeed")
                        .payload(
                                UserAccountResponseServicePayLoadDTO.builder()
                                        .userId("mine702@naver.com")
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
        mockMvc.perform(
                        post("/api/bank/user/accountTransfer")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(accountTransferRequestDTO))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-account-transfer",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestFields(
                                        fieldWithPath("userEmail").type(JsonFieldType.STRING)
                                                .description("유저 이메일"),
                                        fieldWithPath("depositBankCode").type(JsonFieldType.STRING)
                                                .description("입금 계좌 은행 코드"),
                                        fieldWithPath("depositAccountNo").type(JsonFieldType.STRING)
                                                .description("입금 계좌 번호"),
                                        fieldWithPath("transactionBalance").type(JsonFieldType.STRING)
                                                .description("계좌이체 금액"),
                                        fieldWithPath("withdrawalBankCode").type(JsonFieldType.STRING)
                                                .description("입금 계좌 은행 코드"),
                                        fieldWithPath("withdrawalAccountNo").type(JsonFieldType.STRING)
                                                .description("출금 계좌 번호"),
                                        fieldWithPath("depositTransactionSummary").type(JsonFieldType.STRING)
                                                .description("입금 문구")
                                                .optional(),
                                        fieldWithPath("withdrawalTransactionSummary").type(JsonFieldType.STRING)
                                                .description("출금 문구")
                                                .optional()

                                ),
                                responseFields(
                                        fieldWithPath("code").type(JsonFieldType.NUMBER)
                                                .description("코드"),
                                        fieldWithPath("status").type(JsonFieldType.STRING)
                                                .description("상태"),
                                        fieldWithPath("message").type(JsonFieldType.STRING)
                                                .description("메시지"),
                                        fieldWithPath("data").type(JsonFieldType.NULL)
                                                .description("응답 데이터")
                                )
                        )
                );
        // then
    }

    @DisplayName("사용자 주식 구매")
    @Test
    void 사용자_주식_구매() throws Exception {
        // given
        AccountDrawingTransferControllerDTO accountDrawingTransferControllerDTO = AccountDrawingTransferControllerDTO.builder()
                .userEmail("mine702@naver.com")
                .bankCode("004")
                .accountNo("0013386179553277")
                .transactionBalance("34000")
                .transactionSummary("에스케이 하이닉스")
                .build();

        given(bankServiceAPI.getUserAccount(any(UserAccountRequestServiceDTO.class)))
                .willReturn(UserAccountResponseServiceDTO.builder()
                        .code("succeed")
                        .payload(
                                UserAccountResponseServicePayLoadDTO.builder()
                                        .userId("mine702@naver.com")
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
        mockMvc.perform(
                        post("/api/bank/user/drawingTransfer")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(accountDrawingTransferControllerDTO))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-account-drawing-transfer",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestFields(
                                        fieldWithPath("userEmail").type(JsonFieldType.STRING)
                                                .description("유저 이메일"),
                                        fieldWithPath("bankCode").type(JsonFieldType.STRING)
                                                .description("출금 계좌 은행 코드"),
                                        fieldWithPath("accountNo").type(JsonFieldType.STRING)
                                                .description("출금 계좌 번호"),
                                        fieldWithPath("transactionBalance").type(JsonFieldType.STRING)
                                                .description("출금 금액"),
                                        fieldWithPath("transactionSummary").type(JsonFieldType.STRING)
                                                .description("구매 아이템")
                                ),
                                responseFields(
                                        fieldWithPath("code").type(JsonFieldType.NUMBER)
                                                .description("코드"),
                                        fieldWithPath("status").type(JsonFieldType.STRING)
                                                .description("상태"),
                                        fieldWithPath("message").type(JsonFieldType.STRING)
                                                .description("메시지"),
                                        fieldWithPath("data").type(JsonFieldType.NULL)
                                                .description("응답 데이터")
                                )
                        )
                );
        // then
    }


}