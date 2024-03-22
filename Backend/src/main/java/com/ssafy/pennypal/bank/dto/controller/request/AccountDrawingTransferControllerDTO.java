package com.ssafy.pennypal.bank.dto.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountDrawingTransferControllerDTO {

    @Email
    @NotBlank(message = "사용자 이메일은 필수 값입니다")
    private String userEmail;

    @NotBlank(message = "은행 코드를 입력해 주세요 증권 계좌시 004 일반 계좌시 001")
    private String bankCode;

    @NotBlank(message = "계좌 번호를 입력해 주세요")
    private String accountNo;

    @NotBlank(message = "결제 금액은 필수 값입니다")
    private String transactionBalance;

    @NotBlank(message = "결제 아이템을 입력 해 주세요")
    private String transactionSummary;

    @Builder
    public AccountDrawingTransferControllerDTO(String userEmail, String bankCode, String accountNo, String transactionBalance, String transactionSummary) {
        this.userEmail = userEmail;
        this.bankCode = bankCode;
        this.accountNo = accountNo;
        this.transactionBalance = transactionBalance;
        this.transactionSummary = transactionSummary;
    }
}
