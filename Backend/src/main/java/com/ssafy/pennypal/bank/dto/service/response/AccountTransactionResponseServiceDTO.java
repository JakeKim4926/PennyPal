package com.ssafy.pennypal.bank.dto.service.response;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountTransactionResponseServiceDTO {

    private String transactionUniqueNo;
    private String transactionDate;
    private String transactionTime;
    private String transactionType;
    private String transactionTypeName;
    private String transactionBalance;
    private String transactionAfterBalance;
    private String transactionSummary;
    private String transactionAccountNo;

    @Builder
    public AccountTransactionResponseServiceDTO(String transactionUniqueNo, String transactionDate, String transactionTime, String transactionType, String transactionTypeName, String transactionBalance, String transactionAfterBalance, String transactionSummary, String transactionAccountNo) {
        this.transactionUniqueNo = transactionUniqueNo;
        this.transactionDate = transactionDate;
        this.transactionTime = transactionTime;
        this.transactionType = transactionType;
        this.transactionTypeName = transactionTypeName;
        this.transactionBalance = transactionBalance;
        this.transactionAfterBalance = transactionAfterBalance;
        this.transactionSummary = transactionSummary;
        this.transactionAccountNo = transactionAccountNo;
    }
}
