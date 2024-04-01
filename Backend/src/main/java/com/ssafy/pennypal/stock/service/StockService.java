package com.ssafy.pennypal.stock.service;

import com.ssafy.pennypal.stock.dto.response.StockWithLatestTransactionDto;
import com.ssafy.pennypal.stock.repository.stock.IStockRepository;
import com.ssafy.pennypal.stock.repository.stocktransaction.IStockTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StockService implements IStockService {

    private final IStockRepository stockRepository;
    private final IStockTransactionRepository stockTransactionRepository;

    @Override
    public Page<StockWithLatestTransactionDto> getStockList(Pageable pageable) {
        return stockRepository.findStocksWithLatestTransaction(pageable);
    }

    @Override
    public void getStock(String stock) {
        
    }
}
