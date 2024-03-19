package com.ssafy.pennypal.bank.dto.service.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class UserAccountResponseServiceDTO {
    private String code;

    private UserAccountResponseServicePayLoadDTO payload;

    private String now;

    @Builder
    public UserAccountResponseServiceDTO(String code, UserAccountResponseServicePayLoadDTO payload, String now) {
        this.code = code;
        this.payload = payload;
        this.now = now;
    }
}
