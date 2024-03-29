package com.ssafy.pennypal.stock.repository.stock;

import com.ssafy.pennypal.stock.dto.response.StockWithLatestTransactionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IStockRepositoryCustom {

    Page<StockWithLatestTransactionDto> findStocksWithLatestTransaction(Pageable pageable);
}
