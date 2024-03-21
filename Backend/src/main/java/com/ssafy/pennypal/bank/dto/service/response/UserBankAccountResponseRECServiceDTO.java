package com.ssafy.pennypal.bank.dto.service.response;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserBankAccountResponseRECServiceDTO {

    private String bankCode;
    private String accountNo;

    @Builder
    public UserBankAccountResponseRECServiceDTO(String bankCode, String accountNo) {
        this.bankCode = bankCode;
        this.accountNo = accountNo;
    }
}
