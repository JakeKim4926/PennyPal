package com.ssafy.pennypal.stock.repository.stock;

import com.ssafy.pennypal.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IStockRepository extends JpaRepository<Stock, Long>, QuerydslPredicateExecutor<Stock>, IStockRepositoryCustom {
    
}
