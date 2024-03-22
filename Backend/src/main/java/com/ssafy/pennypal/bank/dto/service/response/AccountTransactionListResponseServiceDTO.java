package com.ssafy.pennypal.bank.dto.service.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.pennypal.bank.dto.service.common.CommonHeaderResponseDTO;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountTransactionListResponseServiceDTO {

    @JsonProperty("Header")
    private CommonHeaderResponseDTO Header;

    @JsonProperty("REC")
    private AccountTransactionRecResponseDTO REC;

    @Builder
    public AccountTransactionListResponseServiceDTO(CommonHeaderResponseDTO header, AccountTransactionRecResponseDTO REC) {
        Header = header;
        this.REC = REC;
    }

}
