package com.ssafy.pennypal.bank.dto.service.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.pennypal.bank.dto.service.common.CommonHeaderResponseDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserBankAccountsResponseServiceDTO {

    @JsonProperty("Header")
    private CommonHeaderResponseDTO Header;

    @JsonProperty("REC")
    private List<UserAccountListResponseServiceDTO> REC = new ArrayList<>();

    @Builder
    public UserBankAccountsResponseServiceDTO(CommonHeaderResponseDTO header, List<UserAccountListResponseServiceDTO> REC) {
        this.Header = header;
        this.REC = REC;
    }
}
