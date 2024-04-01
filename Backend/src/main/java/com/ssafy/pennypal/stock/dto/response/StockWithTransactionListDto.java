package com.ssafy.pennypal.stock.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockWithTransactionListDto {

    private LocalDate basDt;

    private double stckGenrDvdnAmt;

    @Builder
    @QueryProjection
    public StockWithTransactionListDto(LocalDate basDt, double stckGenrDvdnAmt) {
        this.basDt = basDt;
        this.stckGenrDvdnAmt = stckGenrDvdnAmt;
    }
}
