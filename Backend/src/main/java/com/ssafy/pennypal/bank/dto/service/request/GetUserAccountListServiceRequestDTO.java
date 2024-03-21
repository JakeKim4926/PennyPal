package com.ssafy.pennypal.bank.dto.service.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.pennypal.bank.dto.service.common.CommonHeaderRequestDTO;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetUserAccountListServiceRequestDTO {

    @JsonProperty("Header")
    private CommonHeaderRequestDTO Header;

    @Builder
    public GetUserAccountListServiceRequestDTO(CommonHeaderRequestDTO header) {
        Header = header;
    }
}
