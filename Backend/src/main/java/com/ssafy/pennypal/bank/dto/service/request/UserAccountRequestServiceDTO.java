package com.ssafy.pennypal.bank.dto.service.request;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAccountRequestServiceDTO {

    private String apiKey;

    private String userId;

    @Builder
    private UserAccountRequestServiceDTO(String apiKey, String userId) {
        this.apiKey = apiKey;
        this.userId = userId;
    }

}
