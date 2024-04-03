package com.ssafy.pennypal.stock.repository.stock;

import com.ssafy.pennypal.stock.dto.request.SearchStockRequestDto;
import com.ssafy.pennypal.stock.dto.response.StockWithLatestTransactionDto;
import com.ssafy.pennypal.stock.dto.response.StockWithTransactionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IStockRepositoryCustom {

    Page<StockWithLatestTransactionDto> findStocksWithLatestTransaction(SearchStockRequestDto searchStockRequestDto, Pageable pageable);

    StockWithTransactionDto findStocksWithTransaction(Long stockId);

}
