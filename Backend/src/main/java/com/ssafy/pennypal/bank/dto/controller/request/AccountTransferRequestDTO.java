package com.ssafy.pennypal.bank.dto.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountTransferRequestDTO {

    @Email
    @NotBlank(message = "유저 이메일은 필수 값입니다")
    private String userEmail;

    @NotBlank(message = "입금 계좌 은행 코드는 필수 값입니다")
    private String depositBankCode;

    @NotBlank(message = "입금 계좌 번호는 필수 값입니다")
    private String depositAccountNo;

    @NotBlank(message = "계좌이체 금액을 비워 둘 수 없습니다")
    private String transactionBalance;

    @NotBlank(message = "입금 계좌 은행 코드는 필수 값입니다")
    private String withdrawalBankCode;

    @NotBlank(message = "출금 계좌 번호는 필수 값입니다")
    private String withdrawalAccountNo;

    @Builder.Default
    private String depositTransactionSummary = "입금";

    @Builder.Default
    private String withdrawalTransactionSummary = "출금";
}
