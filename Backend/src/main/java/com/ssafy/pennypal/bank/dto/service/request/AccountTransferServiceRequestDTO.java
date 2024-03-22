package com.ssafy.pennypal.bank.dto.service.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.pennypal.bank.dto.service.common.CommonHeaderRequestDTO;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountTransferServiceRequestDTO {

    @JsonProperty("Header")
    private CommonHeaderRequestDTO header;

    private String depositBankCode;
    private String depositAccountNo;
    private String depositTransactionSummary;
    private String transactionBalance;
    private String withdrawalBankCode;
    private String withdrawalAccountNo;
    private String withdrawalTransactionSummary;

    @Builder
    public AccountTransferServiceRequestDTO(CommonHeaderRequestDTO header, String depositBankCode, String depositAccountNo, String depositTransactionSummary, String transactionBalance, String withdrawalBankCode, String withdrawalAccountNo, String withdrawalTransactionSummary) {
        this.header = header;
        this.depositBankCode = depositBankCode;
        this.depositAccountNo = depositAccountNo;
        this.depositTransactionSummary = depositTransactionSummary;
        this.transactionBalance = transactionBalance;
        this.withdrawalBankCode = withdrawalBankCode;
        this.withdrawalAccountNo = withdrawalAccountNo;
        this.withdrawalTransactionSummary = withdrawalTransactionSummary;
    }
}
