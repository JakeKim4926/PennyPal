package com.ssafy.pennypal.bank.dto.service.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.pennypal.bank.dto.service.common.CommonHeaderResponseDTO;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountDepositWithdrawalResponseServiceDTO {

    @JsonProperty("Header")
    private CommonHeaderResponseDTO Header;

    @JsonProperty("REC")
    private DepositWithdrawalResponseServiceDTO REC;

    @Builder
    public AccountDepositWithdrawalResponseServiceDTO(CommonHeaderResponseDTO header, DepositWithdrawalResponseServiceDTO REC) {
        Header = header;
        this.REC = REC;
    }
}
