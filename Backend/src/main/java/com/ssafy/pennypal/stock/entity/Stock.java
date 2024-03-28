package com.ssafy.pennypal.stock.entity;

import com.querydsl.core.annotations.QueryProjection;
import com.ssafy.pennypal.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stock extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Long stockId;

    @Column(name = "stock_crno")
    private String crno;

    @Column(name = "stock_isinCd")
    private String isinCd;

    @Column(name = "stock_stckIssuCmpyNm")
    private String stckIssuCmpyNm;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "stckGenrDvdnAmt",
            cascade = CascadeType.ALL
    )
    private List<StockTransaction> stockTransactions = new ArrayList<>();

    @Builder
    @QueryProjection
    public Stock(String crno, String isinCd, String stckIssuCmpyNm, List<StockTransaction> stockTransactions) {
        this.crno = crno;
        this.isinCd = isinCd;
        this.stckIssuCmpyNm = stckIssuCmpyNm;
        this.stockTransactions = stockTransactions;
    }
}
