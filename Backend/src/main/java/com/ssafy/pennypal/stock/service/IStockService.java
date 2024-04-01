package com.ssafy.pennypal.stock.service;

import com.ssafy.pennypal.stock.dto.response.StockWithLatestTransactionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IStockService {

    Page<StockWithLatestTransactionDto> getStockList(Pageable pageable);

    void getStock(String stock);
}
