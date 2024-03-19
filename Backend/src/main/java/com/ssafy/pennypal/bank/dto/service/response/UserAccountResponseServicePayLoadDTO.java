package com.ssafy.pennypal.bank.dto.service.response;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAccountResponseServicePayLoadDTO {

    private String userEmail;
    private String userName;
    private String institutionCode;
    private String userKey;
    private String created;
    private String modified;

    @Builder
    public UserAccountResponseServicePayLoadDTO(String userEmail, String userName, String institutionCode, String userKey, String created, String modified) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.institutionCode = institutionCode;
        this.userKey = userKey;
        this.created = created;
        this.modified = modified;
    }
}
