package com.ssafy.pennypal.bank.dto.service.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class UserAccountResponseServicePayLoadDTO {

    private String userId;
    private String userName;
    private String institutionCode;
    private String userKey;
    private String created;
    private String modified;

    @Builder
    public UserAccountResponseServicePayLoadDTO(String userId, String userName, String institutionCode, String userKey, String created, String modified) {
        this.userId = userId;
        this.userName = userName;
        this.institutionCode = institutionCode;
        this.userKey = userKey;
        this.created = created;
        this.modified = modified;
    }
}
