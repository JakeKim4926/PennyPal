package com.ssafy.pennypal.bank.dto.service.request;

import com.ssafy.pennypal.bank.dto.service.common.CommonHeaderRequestDTO;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserBankAccountRequestServiceDTO {

    private CommonHeaderRequestDTO Header;

    private String accountTypeUniqueNo;

    @Builder
    public UserBankAccountRequestServiceDTO(CommonHeaderRequestDTO header, String accountTypeUniqueNo) {
        Header = header;
        this.accountTypeUniqueNo = accountTypeUniqueNo;
    }
}
