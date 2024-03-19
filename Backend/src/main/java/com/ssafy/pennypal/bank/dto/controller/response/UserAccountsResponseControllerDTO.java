package com.ssafy.pennypal.bank.dto.controller.response;

import com.ssafy.pennypal.bank.dto.service.response.UserBankAccountsResponseServiceDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAccountsResponseControllerDTO {

    private CommonHeaderResponseControllerDTO Header;
    private List<UserAccountListResponseControllerDTO> REC = new ArrayList<>();

    @Builder
    public UserAccountsResponseControllerDTO(CommonHeaderResponseControllerDTO header, List<UserAccountListResponseControllerDTO> REC) {
        this.Header = header;
        this.REC = REC;
    }

    public static UserAccountsResponseControllerDTO of(UserBankAccountsResponseServiceDTO serviceDTO) {

        List<UserAccountListResponseControllerDTO> convertedREC = serviceDTO.getREC().stream()
                .map(serviceListDTO -> UserAccountListResponseControllerDTO.builder()
                        .bankCode(serviceListDTO.getBankCode())
                        .bankName(serviceListDTO.getBankName())
                        .accountNo(serviceListDTO.getAccountNo())
                        .build())
                .toList();

        return UserAccountsResponseControllerDTO.builder()
                .header(
                        CommonHeaderResponseControllerDTO.builder()
                                .responseCode(serviceDTO.getHeader().getResponseCode())
                                .responseMessage(serviceDTO.getHeader().getResponseMessage())
                                .build()
                )
                .REC(convertedREC)
                .build();
    }
}
