package com.ssafy.pennypal.stock.dto.request;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchStockRequestDto {

    private String stckIssuCmpyNm;

    private Double startPrice;

    private Double endPrice;

    @Builder
    public SearchStockRequestDto(String stckIssuCmpyNm, Double startPrice, Double endPrice) {
        this.stckIssuCmpyNm = stckIssuCmpyNm;
        this.startPrice = startPrice;
        this.endPrice = endPrice;
    }
}
