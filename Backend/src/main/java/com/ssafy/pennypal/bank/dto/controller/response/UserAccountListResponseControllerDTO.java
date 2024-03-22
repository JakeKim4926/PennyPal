package com.ssafy.pennypal.bank.dto.controller.response;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAccountListResponseControllerDTO {

    private String bankCode;
    private String bankName;
    private String accountNo;

    @Builder
    public UserAccountListResponseControllerDTO(String bankCode, String bankName, String accountNo) {
        this.bankCode = bankCode;
        this.bankName = bankName;
        this.accountNo = accountNo;
    }
}
