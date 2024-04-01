package com.ssafy.pennypal.stock.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockWithTransactionDto {

    private Long stockId;

    private String crno;

    private String isinCd;

    private String stckIssuCmpyNm;

    private List<StockWithTransactionListDto> stockWithTransactionListDtoList = new ArrayList<>();

    @Builder
    @QueryProjection
    public StockWithTransactionDto(Long stockId, String crno, String isinCd, String stckIssuCmpyNm, List<StockWithTransactionListDto> stockWithTransactionListDtoList) {
        this.stockId = stockId;
        this.crno = crno;
        this.isinCd = isinCd;
        this.stckIssuCmpyNm = stckIssuCmpyNm;
        this.stockWithTransactionListDtoList = stockWithTransactionListDtoList;
    }

    @Builder
    @QueryProjection
    public StockWithTransactionDto(Long stockId, String crno, String isinCd, String stckIssuCmpyNm) {
        this.stockId = stockId;
        this.crno = crno;
        this.isinCd = isinCd;
        this.stckIssuCmpyNm = stckIssuCmpyNm;
    }

}
