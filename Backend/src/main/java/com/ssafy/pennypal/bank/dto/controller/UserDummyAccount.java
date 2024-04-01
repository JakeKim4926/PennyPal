package com.ssafy.pennypal.bank.dto.controller;

import com.ssafy.pennypal.bank.dto.controller.response.UserAccountsResponseControllerDTO;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDummyAccount {

    private String bankCode;
    private String bankName;
    private String accountNo;

    @Builder
    public UserDummyAccount(String bankCode, String bankName, String accountNo) {
        this.bankCode = bankCode;
        this.bankName = bankName;
        this.accountNo = accountNo;
    }

    public static UserDummyAccount of(UserAccountsResponseControllerDTO controllerDTO) {
        return controllerDTO.getREC().stream()
                .filter(userAccountListResponseControllerDTO -> "001".equals(userAccountListResponseControllerDTO.getBankCode()))
                .map(userAccountListResponseControllerDTO -> UserDummyAccount.builder()
                        .bankCode(userAccountListResponseControllerDTO.getBankCode())
                        .bankName(userAccountListResponseControllerDTO.getBankName())
                        .accountNo(userAccountListResponseControllerDTO.getAccountNo())
                        .build())
                .findFirst()
                .orElse(null); // 스트림에서 첫 번째로 찾은 요소를 반환하거나, 없으면 null 반환
    }


}
