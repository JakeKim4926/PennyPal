package com.ssafy.pennypal.bank.dto.service.request;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserApiKeyRequestDTO {

    private String userId;

    private String userKey;

    @Builder
    public UserApiKeyRequestDTO(String userId, String userKey) {
        this.userId = userId;
        this.userKey = userKey;
    }
}
