package com.ssafy.pennypal.bank.dto.service.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class UserAccountRequestServiceDTO {

    private String apiKey;

    private String userId;

    @Builder
    private UserAccountRequestServiceDTO(String apiKey, String userId) {
        this.apiKey = apiKey;
        this.userId = userId;
    }

}
