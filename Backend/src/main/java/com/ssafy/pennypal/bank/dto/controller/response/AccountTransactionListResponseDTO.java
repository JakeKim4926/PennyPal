package com.ssafy.pennypal.bank.dto.controller.response;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountTransactionListResponseDTO {
    private String transactionUniqueNo;
    private String transactionDate;
    private String transactionType;
    private String transactionSummary;

    @Builder
    public AccountTransactionListResponseDTO(String transactionUniqueNo, String transactionDate, String transactionType, String transactionSummary) {
        this.transactionUniqueNo = transactionUniqueNo;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.transactionSummary = transactionSummary;
    }
}
