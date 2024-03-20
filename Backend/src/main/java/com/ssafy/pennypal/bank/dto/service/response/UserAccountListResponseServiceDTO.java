package com.ssafy.pennypal.bank.dto.service.response;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAccountListResponseServiceDTO {
    
    private String bankCode;
    private String bankName;
    private String userName;
    private String accountNo;
    private String accountName;
    private String accountTypeCode;
    private String accountTypeName;
    private String accountCreatedDate;
    private String accountExpiryDate;
    private String dailyTransferLimit;
    private String oneTimeTransferLimit;
    private String accountBalance;

    @Builder
    public UserAccountListResponseServiceDTO(String bankCode, String bankName, String userName, String accountNo, String accountName, String accountTypeCode, String accountTypeName, String accountCreatedDate, String accountExpiryDate, String dailyTransferLimit, String oneTimeTransferLimit, String accountBalance) {
        this.bankCode = bankCode;
        this.bankName = bankName;
        this.userName = userName;
        this.accountNo = accountNo;
        this.accountName = accountName;
        this.accountTypeCode = accountTypeCode;
        this.accountTypeName = accountTypeName;
        this.accountCreatedDate = accountCreatedDate;
        this.accountExpiryDate = accountExpiryDate;
        this.dailyTransferLimit = dailyTransferLimit;
        this.oneTimeTransferLimit = oneTimeTransferLimit;
        this.accountBalance = accountBalance;
    }
}
