package com.ssafy.pennypal.bank.dto.service.request;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAccountRequestServiceDTO {

    private String apiKey;

    private String userEmail;

    @Builder
    private UserAccountRequestServiceDTO(String apiKey, String userEmail) {
        this.apiKey = apiKey;
        this.userEmail = userEmail;
    }

}
