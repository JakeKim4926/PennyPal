package com.ssafy.pennypal.stock.repository.stock;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.ssafy.pennypal.global.common.support.Querydsl5RepositorySupport;
import com.ssafy.pennypal.stock.dto.response.*;
import com.ssafy.pennypal.stock.entity.QStock;
import com.ssafy.pennypal.stock.entity.QStockTransaction;
import com.ssafy.pennypal.stock.entity.Stock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Slf4j
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

    @Override
    public StockWithTransactionDto findStocksWithTransaction(Long stockId) {
        log.info("stockId = {}", stockId);
        QStock stock = QStock.stock;
        QStockTransaction stockTransaction = QStockTransaction.stockTransaction;

        return Optional.ofNullable(select(stock)
                        .from(stock)
                        .where(stock.stockId.eq(stockId))
                        .fetchOne())
                .map(fetchOne -> {
                    List<StockWithTransactionListDto> transactions = select(new QStockWithTransactionListDto(
                            stockTransaction.basDt,
                            stockTransaction.stckGenrDvdnAmt))
                            .from(stockTransaction)
                            .where(stockTransaction.stock.stockId.eq(stockId))
                            .fetch();

                    return StockWithTransactionDto.builder()
                            .stockId(fetchOne.getStockId())
                            .crno(fetchOne.getCrno())
                            .isinCd(fetchOne.getIsinCd())
                            .stckIssuCmpyNm(fetchOne.getStckIssuCmpyNm())
                            .stockWithTransactionListDtoList(transactions)
                            .build();
                })
                .orElse(null);
    }
}
