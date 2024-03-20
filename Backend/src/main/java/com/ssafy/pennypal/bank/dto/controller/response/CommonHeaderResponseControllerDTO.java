package com.ssafy.pennypal.bank.dto.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class CommonHeaderResponseControllerDTO {

    private String responseCode;
    private String responseMessage;

    @Builder
    public CommonHeaderResponseControllerDTO(String responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }
}
