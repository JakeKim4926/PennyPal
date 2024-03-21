package com.ssafy.pennypal.bank.dto.controller.response;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCreateAccountRECControllerDTO {

    private String bankCode;
    private String accountNo;

    @Builder
    public UserCreateAccountRECControllerDTO(String bankCode, String accountNo) {
        this.bankCode = bankCode;
        this.accountNo = accountNo;
    }
}
