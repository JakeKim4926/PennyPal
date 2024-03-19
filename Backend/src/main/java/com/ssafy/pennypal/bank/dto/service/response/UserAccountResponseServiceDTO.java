package com.ssafy.pennypal.bank.dto.service.response;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
