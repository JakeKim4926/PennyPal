package com.ssafy.pennypal.bank.dto.service.response;

import com.ssafy.pennypal.bank.dto.service.common.CommonHeaderResponseDTO;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountDepositWithdrawalResponseServiceDTO {
    private CommonHeaderResponseDTO Header;
    private DepositWithdrawalResponseServiceDTO REC;

    @Builder
    public AccountDepositWithdrawalResponseServiceDTO(CommonHeaderResponseDTO header, DepositWithdrawalResponseServiceDTO REC) {
        Header = header;
        this.REC = REC;
    }
}
