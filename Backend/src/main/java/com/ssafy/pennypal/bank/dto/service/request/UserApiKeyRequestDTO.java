package com.ssafy.pennypal.bank.dto.service.request;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserApiKeyRequestDTO {

    private String userEmail;

    private String userKey;

    @Builder
    public UserApiKeyRequestDTO(String userEmail, String userKey) {
        this.userEmail = userEmail;
        this.userKey = userKey;
    }
}
