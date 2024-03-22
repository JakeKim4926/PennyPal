package com.ssafy.pennypal.bank.dto.service.common;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonHeaderResponseDTO {

    private String responseCode;
    private String responseMessage;
    private String apiName;
    private String transmissionDate;
    private String transmissionTime;
    private String institutionCode;
    private String apiKey;
    private String apiServiceCode;
    private String institutionTransactionUniqueNo;

    @Builder
    public CommonHeaderResponseDTO(String responseCode, String responseMessage, String apiName, String transmissionDate, String transmissionTime, String institutionCode, String apiKey, String apiServiceCode, String institutionTransactionUniqueNo) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.apiName = apiName;
        this.transmissionDate = transmissionDate;
        this.transmissionTime = transmissionTime;
        this.institutionCode = institutionCode;
        this.apiKey = apiKey;
        this.apiServiceCode = apiServiceCode;
        this.institutionTransactionUniqueNo = institutionTransactionUniqueNo;
    }
}
