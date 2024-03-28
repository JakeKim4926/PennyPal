package com.ssafy.pennypal.stock.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockWithLatestTransactionDto {

    private Long stockId;

    private String crno;

    private String isinCd;

    private String stckIssuCmpyNm;

    private String basDt;

    private String stckGenrDvdnAmt;

    @Builder
    @QueryProjection
    public StockWithLatestTransactionDto(Long stockId, String crno, String isinCd, String stckIssuCmpyNm, String basDt, String stckGenrDvdnAmt) {
        this.stockId = stockId;
        this.crno = crno;
        this.isinCd = isinCd;
        this.stckIssuCmpyNm = stckIssuCmpyNm;
        this.basDt = basDt;
        this.stckGenrDvdnAmt = stckGenrDvdnAmt;
    }
}
