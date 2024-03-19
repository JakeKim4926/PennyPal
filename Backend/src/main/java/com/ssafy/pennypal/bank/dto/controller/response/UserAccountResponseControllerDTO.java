package com.ssafy.pennypal.bank.dto.controller.response;

import com.ssafy.pennypal.bank.dto.service.response.UserAccountResponseServiceDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class UserAccountResponseControllerDTO {

    private String userEmail;
    private String userKey;
    private String created;
    private String modified;

    @Builder
    public UserAccountResponseControllerDTO(String userEmail, String userKey, String created, String modified) {
        this.userEmail = userEmail;
        this.userKey = userKey;
        this.created = created;
        this.modified = modified;
    }

    public static UserAccountResponseControllerDTO of(UserAccountResponseServiceDTO serviceDTO) {
        return UserAccountResponseControllerDTO.builder()
                .userEmail(serviceDTO.getPayload().getUserEmail())
                .userKey(serviceDTO.getPayload().getUserKey())
                .created(serviceDTO.getPayload().getCreated())
                .modified(serviceDTO.getPayload().getModified())
                .build();
    }
}
