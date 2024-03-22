package com.ssafy.pennypal.bank.dto.service.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.pennypal.bank.dto.service.common.CommonHeaderRequestDTO;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountTransactionRequestServiceDTO {

    @JsonProperty("Header")
    private CommonHeaderRequestDTO Header;

    private String bankCode;
    private String accountNo;
    private String startDate;
    private String endDate;
    private String transactionType;
    private String orderByType;

    @Builder
    public AccountTransactionRequestServiceDTO(CommonHeaderRequestDTO header, String bankCode, String accountNo, String startDate, String endDate, String transactionType, String orderByType) {
        Header = header;
        this.bankCode = bankCode;
        this.accountNo = accountNo;
        this.startDate = startDate;
        this.endDate = endDate;
        this.transactionType = transactionType;
        this.orderByType = orderByType;
    }
}
