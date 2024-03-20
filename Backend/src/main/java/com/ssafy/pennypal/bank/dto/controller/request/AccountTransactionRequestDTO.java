package com.ssafy.pennypal.bank.dto.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Getter
@ToString
@Validated
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountTransactionRequestDTO {

    @NotBlank(message = "사용자 이메일은 필수 값입니다")
    private String userEmail;

    @NotBlank(message = "은행 코드를 입력해 주세요 증권 계좌시 004 일반 계좌시 001")
    private String bankCode;

    @NotBlank(message = "조회할 타입을 입력 해 주세요 전체 : A 출금 : D 입금 : M ")
    private String transactionType;

    @NotBlank(message = "계좌 번호를 입력해 주세요")
    private String accountNo;

    @NotBlank(message = "시작 날짜를 입력해 주세요")
    private String startDate;

    @NotBlank(message = "종료 날짜를 입력해 주세요")
    private String endDate;

    @Builder
    public AccountTransactionRequestDTO(String userEmail, String bankCode, String transactionType, String accountNo, String startDate, String endDate) {
        this.userEmail = userEmail;
        this.bankCode = bankCode;
        this.transactionType = transactionType;
        this.accountNo = accountNo;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
