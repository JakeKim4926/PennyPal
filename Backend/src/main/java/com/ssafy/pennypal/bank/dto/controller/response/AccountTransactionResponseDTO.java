package com.ssafy.pennypal.bank.dto.controller.response;

import com.ssafy.pennypal.bank.dto.service.response.AccountTransactionListResponseServiceDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountTransactionResponseDTO {
    private CommonHeaderResponseControllerDTO Header;

    private List<AccountTransactionListResponseDTO> REC = new ArrayList<>();

    @Builder
    public AccountTransactionResponseDTO(CommonHeaderResponseControllerDTO header, List<AccountTransactionListResponseDTO> REC) {
        Header = header;
        this.REC = REC;
    }

    public static AccountTransactionResponseDTO of(AccountTransactionListResponseServiceDTO serviceDTO) {

        List<AccountTransactionListResponseDTO> convertedREC = serviceDTO.getREC().getList().stream()
                .map(serviceListDTO -> AccountTransactionListResponseDTO.builder()
                        .transactionUniqueNo(serviceListDTO.getTransactionUniqueNo())
                        .transactionDate(serviceListDTO.getTransactionDate())
                        .transactionType(serviceListDTO.getTransactionType())
                        .transactionSummary(serviceListDTO.getTransactionSummary())
                        .build())
                .toList();
        
        return AccountTransactionResponseDTO.builder()
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
