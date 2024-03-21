package com.ssafy.pennypal.bank.dto.service.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.pennypal.bank.dto.service.common.CommonHeaderRequestDTO;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DrawingTransferRequestServiceDTO {

    @JsonProperty("Header")
    private CommonHeaderRequestDTO Header;
    private String bankCode;
    private String accountNo;
    private String transactionBalance;
    private String transactionSummary;

    @Builder
    public DrawingTransferRequestServiceDTO(CommonHeaderRequestDTO header, String bankCode, String accountNo, String transactionBalance, String transactionSummary) {
        Header = header;
        this.bankCode = bankCode;
        this.accountNo = accountNo;
        this.transactionBalance = transactionBalance;
        this.transactionSummary = transactionSummary;
    }
}
