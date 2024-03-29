package com.ssafy.pennypal.stock.repository.stocktransaction;

import com.ssafy.pennypal.global.common.support.Querydsl5RepositorySupport;
import com.ssafy.pennypal.stock.entity.StockTransaction;

public class IStockTransactionRepositoryImpl extends Querydsl5RepositorySupport implements IStockTransactionRepositoryCustom {

    public IStockTransactionRepositoryImpl() {
        super(StockTransaction.class);
    }

}
