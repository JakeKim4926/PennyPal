package com.ssafy.pennypal.bank.dto.service.response;

import com.ssafy.pennypal.bank.dto.service.common.CommonHeaderResponseDTO;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountTransactionListResponseServiceDTO {

    private CommonHeaderResponseDTO Header;

    private AccountTransactionRecResponseDTO REC;

    @Builder
    public AccountTransactionListResponseServiceDTO(CommonHeaderResponseDTO header, AccountTransactionRecResponseDTO REC) {
        Header = header;
        this.REC = REC;
    }

}
