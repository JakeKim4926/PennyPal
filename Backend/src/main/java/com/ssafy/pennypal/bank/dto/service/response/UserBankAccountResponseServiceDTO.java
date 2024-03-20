package com.ssafy.pennypal.bank.dto.service.response;

import com.ssafy.pennypal.bank.dto.service.common.CommonHeaderResponseDTO;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserBankAccountResponseServiceDTO {

    private CommonHeaderResponseDTO Header;
    private String accountTypeUniqueNo;

    @Builder
    public UserBankAccountResponseServiceDTO(CommonHeaderResponseDTO header, String accountTypeUniqueNo) {
        this.Header = header;
        this.accountTypeUniqueNo = accountTypeUniqueNo;
    }
}
