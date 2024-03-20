package com.ssafy.pennypal.bank.dto.service.common;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonHeaderRequestDTO {

    private String apiName;

    private String transmissionDate;

    private String transmissionTime;

    private String institutionCode;

    private String fintechAppNo;

    private String apiServiceCode;

    private String institutionTransactionUniqueNo;

    private String apiKey;

    private String userKey;

    @Builder
    public CommonHeaderRequestDTO(String apiName, String apiKey, String userKey) {
        LocalDateTime now = LocalDateTime.now();

        this.apiName = apiName;

        this.transmissionDate = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        this.transmissionTime = now.format(DateTimeFormatter.ofPattern("HHmmss"));

        this.institutionCode = "00100";

        this.fintechAppNo = "001";

        this.apiServiceCode = apiName;

        this.institutionTransactionUniqueNo =
                now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + (100000 + new Random().nextInt(900000));

        this.apiKey = apiKey;

        this.userKey = userKey;
    }
}
