package com.ssafy.pennypal.bank.dto.service.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.pennypal.bank.dto.service.common.CommonHeaderResponseDTO;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserBankAccountResponseServiceDTO {

    @JsonProperty("Header") // JSON 키 "Header"와 매핑
    private CommonHeaderResponseDTO Header;

    @JsonProperty("REC") // JSON 키 "REC"와 매핑
    private UserBankAccountResponseRECServiceDTO REC;

    @Builder
    public UserBankAccountResponseServiceDTO(CommonHeaderResponseDTO Header, UserBankAccountResponseRECServiceDTO REC) {
        this.Header = Header;
        this.REC = REC;
    }
}
