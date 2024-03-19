package com.ssafy.pennypal.bank.dto.service.response;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountTransactionRecResponseDTO {

    private String totalCount;
    private List<AccountTransactionResponseServiceDTO> list = new ArrayList<>();

    @Builder
    public AccountTransactionRecResponseDTO(String totalCount, List<AccountTransactionResponseServiceDTO> list) {
        this.totalCount = totalCount;
        this.list = list;
    }
}
