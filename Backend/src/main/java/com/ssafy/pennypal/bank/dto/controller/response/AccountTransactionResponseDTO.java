package com.ssafy.pennypal.bank.dto.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ssafy.pennypal.bank.dto.service.response.AccountTransactionListResponseServiceDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
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
                        .transactionBalance(serviceListDTO.getTransactionBalance())
                        .transactionSummary(serviceListDTO.getTransactionSummary())
                        .transactionAccountNo(serviceListDTO.getTransactionAccountNo())
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
