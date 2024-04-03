package com.ssafy.pennypal.stock.service;

import com.ssafy.pennypal.stock.dto.request.SearchStockRequestDto;
import com.ssafy.pennypal.stock.dto.response.StockWithLatestTransactionDto;
import com.ssafy.pennypal.stock.dto.response.StockWithTransactionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IStockService {

    Page<StockWithLatestTransactionDto> getStockList(SearchStockRequestDto searchStockRequestDto, Pageable pageable);

    StockWithTransactionDto getStock(Long stockId);

}
