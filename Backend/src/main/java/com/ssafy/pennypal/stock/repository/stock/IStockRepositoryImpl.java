package com.ssafy.pennypal.stock.repository.stock;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.ssafy.pennypal.global.common.support.Querydsl5RepositorySupport;
import com.ssafy.pennypal.stock.dto.request.SearchStockRequestDto;
import com.ssafy.pennypal.stock.dto.response.*;
import com.ssafy.pennypal.stock.entity.QStockTransaction;
import com.ssafy.pennypal.stock.entity.Stock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.ssafy.pennypal.stock.entity.QStock.stock;
import static com.ssafy.pennypal.stock.entity.QStockTransaction.stockTransaction;
import static io.micrometer.common.util.StringUtils.isEmpty;

@Slf4j
public class IStockRepositoryImpl extends Querydsl5RepositorySupport implements IStockRepositoryCustom {

    public IStockRepositoryImpl() {
        super(Stock.class);
    }

    @Override
    public Page<StockWithLatestTransactionDto> findStocksWithLatestTransaction(SearchStockRequestDto searchStockRequestDto, Pageable pageable) {
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
                .where(
                        stckIssuCmpyNmEq(searchStockRequestDto.getStckIssuCmpyNm()),
                        stckGenrDvdnAmtEq(searchStockRequestDto.getStartPrice(), searchStockRequestDto.getEndPrice()),
                        stockTransaction.in(
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

    private BooleanExpression stckIssuCmpyNmEq(String stckIssuCmpyNm) {
        return isEmpty(stckIssuCmpyNm) ? null : stock.stckIssuCmpyNm.contains(stckIssuCmpyNm);
    }

    private BooleanExpression stckGenrDvdnAmtEq(Double startPrice, Double endPrice) {
        return Optional.ofNullable(startPrice)
                .map(price -> Optional.ofNullable(endPrice)
                        .map(endPriceNotNull -> stockTransaction.stckGenrDvdnAmt.between(startPrice, endPriceNotNull))
                        .orElseGet(() -> stockTransaction.stckGenrDvdnAmt.goe(price)))
                .orElseGet(() -> Optional.ofNullable(endPrice)
                        .map(stockTransaction.stckGenrDvdnAmt::loe)
                        .orElse(null));
    }
}
