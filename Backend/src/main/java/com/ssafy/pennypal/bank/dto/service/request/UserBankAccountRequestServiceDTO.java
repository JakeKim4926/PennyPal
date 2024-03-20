package com.ssafy.pennypal.bank.dto.service.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.pennypal.bank.dto.service.common.CommonHeaderRequestDTO;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserBankAccountRequestServiceDTO {
    
    @JsonProperty("Header")
    private CommonHeaderRequestDTO Header;

    private String accountTypeUniqueNo;

    @Builder
    public UserBankAccountRequestServiceDTO(CommonHeaderRequestDTO header, String accountTypeUniqueNo) {
        this.Header = header;
        this.accountTypeUniqueNo = accountTypeUniqueNo;
    }
}
