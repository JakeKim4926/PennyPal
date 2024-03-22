package com.ssafy.pennypal.bank.dto.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountTransactionListResponseDTO {
    private String transactionUniqueNo;
    private String transactionDate;
    private String transactionType;
    private String transactionBalance;
    private String transactionSummary;
    private String transactionAccountNo;

    @Builder
    public AccountTransactionListResponseDTO(String transactionUniqueNo, String transactionDate, String transactionType, String transactionBalance, String transactionSummary, String transactionAccountNo) {
        this.transactionUniqueNo = transactionUniqueNo;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.transactionBalance = transactionBalance;
        this.transactionSummary = transactionSummary;
        this.transactionAccountNo = transactionAccountNo;
    }

}
