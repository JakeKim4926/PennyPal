package com.ssafy.pennypal.stock.entity;

import com.querydsl.core.annotations.QueryProjection;
import com.ssafy.pennypal.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockTransaction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_transaction_id")
    private Long stockTransactionId;

    @Column(name = "stock_transaction_basDt")
    private LocalDate basDt;

    @Column(name = "stock_transaction_stckGenrDvdnAmt")
    private double stckGenrDvdnAmt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id") // 이 부분은 Stock 엔티티의 기본 키 필드명과 일치해야 합니다.
    private Stock stock;

    @Builder
    @QueryProjection
    public StockTransaction(LocalDate basDt, double stckGenrDvdnAmt, Stock stock) {
        this.basDt = basDt;
        this.stckGenrDvdnAmt = stckGenrDvdnAmt;
        this.stock = stock;
    }
}
