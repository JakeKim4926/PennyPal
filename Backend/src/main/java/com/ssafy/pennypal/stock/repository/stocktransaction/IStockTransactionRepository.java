package com.ssafy.pennypal.stock.repository.stocktransaction;

import com.ssafy.pennypal.stock.entity.Stock;
import com.ssafy.pennypal.stock.entity.StockTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IStockTransactionRepository extends JpaRepository<StockTransaction, Long>, QuerydslPredicateExecutor<StockTransaction>, IStockTransactionRepositoryCustom {
    StockTransaction findTop1ByStockOrderByBasDtDesc(Stock stock);
}
