package com.ssafy.pennypal.bank.dto.controller.response;

import com.ssafy.pennypal.bank.dto.service.response.UserBankAccountResponseServiceDTO;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCreateAccountControllerDTO {

    private CommonHeaderResponseControllerDTO Header;
    private UserCreateAccountRECControllerDTO REC;

    @Builder
    public UserCreateAccountControllerDTO(CommonHeaderResponseControllerDTO Header, UserCreateAccountRECControllerDTO REC) {
        this.Header = Header;
        this.REC = REC;
    }

    public static UserCreateAccountControllerDTO of(UserBankAccountResponseServiceDTO serviceDTO) {

        return UserCreateAccountControllerDTO.builder()
                .Header(
                        CommonHeaderResponseControllerDTO.builder()
                                .responseCode(serviceDTO.getHeader().getResponseCode())
                                .responseMessage(serviceDTO.getHeader().getResponseMessage())
                                .build()
                )
                .REC(
                        UserCreateAccountRECControllerDTO.builder()
                                .bankCode(serviceDTO.getREC().getBankCode())
                                .accountNo(serviceDTO.getREC().getAccountNo())
                                .build()
                )
                .build();
    }
}
