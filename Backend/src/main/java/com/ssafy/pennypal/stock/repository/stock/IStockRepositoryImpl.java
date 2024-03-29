package com.ssafy.pennypal.stock.repository.stock;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.ssafy.pennypal.global.common.support.Querydsl5RepositorySupport;
import com.ssafy.pennypal.stock.dto.response.QStockWithLatestTransactionDto;
import com.ssafy.pennypal.stock.dto.response.StockWithLatestTransactionDto;
import com.ssafy.pennypal.stock.entity.QStock;
import com.ssafy.pennypal.stock.entity.QStockTransaction;
import com.ssafy.pennypal.stock.entity.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class IStockRepositoryImpl extends Querydsl5RepositorySupport implements IStockRepositoryCustom {

    public IStockRepositoryImpl() {
        super(Stock.class);
    }

    @Override
    public Page<StockWithLatestTransactionDto> findStocksWithLatestTransaction(Pageable pageable) {
        QStock stock = QStock.stock;
        QStockTransaction stockTransaction = QStockTransaction.stockTransaction;

        JPAQuery<StockWithLatestTransactionDto> query = select(new QStockWithLatestTransactionDto(
                stock.stockId,
                stock.crno,
                stock.isinCd,
                stock.stckIssuCmpyNm,
                stockTransaction.basDt,
                stockTransaction.stckGenrDvdnAmt
        ))
                .from(stock)
                .leftJoin(stock.stockTransactions, stockTransaction)
                .where(stockTransaction.in(
                        JPAExpressions
                                .selectFrom(QStockTransaction.stockTransaction)
                                .where(QStockTransaction.stockTransaction.stock.eq(stock))
                                .orderBy(QStockTransaction.stockTransaction.basDt.desc())
                                .limit(1)))
                .orderBy(stockTransaction.stckGenrDvdnAmt.desc());

        return applyPagination(pageable, contentQuery -> query);
    }
}
