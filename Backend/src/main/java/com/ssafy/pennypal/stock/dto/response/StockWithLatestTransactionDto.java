package com.ssafy.pennypal.stock.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockWithLatestTransactionDto {

    private Long stockId;

    private String crno;

    private String isinCd;

    private String stckIssuCmpyNm;

    private LocalDate basDt;

    private float stckGenrDvdnAmt;

    @Builder
    @QueryProjection

    public StockWithLatestTransactionDto(Long stockId, String crno, String isinCd, String stckIssuCmpyNm, LocalDate basDt, float stckGenrDvdnAmt) {
        this.stockId = stockId;
        this.crno = crno;
        this.isinCd = isinCd;
        this.stckIssuCmpyNm = stckIssuCmpyNm;
        this.basDt = basDt;
        this.stckGenrDvdnAmt = stckGenrDvdnAmt;
    }
}
