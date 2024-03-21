package com.ssafy.pennypal.bank.dto.service.response;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DepositWithdrawalResponseServiceDTO {

    private String transactionUniqueNo;
    private String transactionDate;

    @Builder
    public DepositWithdrawalResponseServiceDTO(String transactionUniqueNo, String transactionDate) {
        this.transactionUniqueNo = transactionUniqueNo;
        this.transactionDate = transactionDate;
    }
}
