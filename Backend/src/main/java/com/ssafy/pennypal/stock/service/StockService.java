package com.ssafy.pennypal.stock.service;

import com.ssafy.pennypal.stock.dto.request.SearchStockRequestDto;
import com.ssafy.pennypal.stock.dto.response.StockWithLatestTransactionDto;
import com.ssafy.pennypal.stock.dto.response.StockWithTransactionDto;
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
    public Page<StockWithLatestTransactionDto> getStockList(SearchStockRequestDto searchStockRequestDto, Pageable pageable) {
        return stockRepository.findStocksWithLatestTransaction(searchStockRequestDto, pageable);
    }

    @Override
    public StockWithTransactionDto getStock(Long stockId) {
        return stockRepository.findStocksWithTransaction(stockId);
    }
    
}
