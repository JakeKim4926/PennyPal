package com.ssafy.pennypal.stock.repository.stock;

import com.ssafy.pennypal.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface IStockRepository extends JpaRepository<Stock, Long>, QuerydslPredicateExecutor<Stock>, IStockRepositoryCustom {

}
