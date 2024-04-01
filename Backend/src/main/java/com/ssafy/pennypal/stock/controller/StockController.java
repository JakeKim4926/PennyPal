package com.ssafy.pennypal.stock.controller;

import com.ssafy.pennypal.global.common.api.ApiResponse;
import com.ssafy.pennypal.stock.dto.response.StockWithLatestTransactionDto;
import com.ssafy.pennypal.stock.dto.response.StockWithTransactionDto;
import com.ssafy.pennypal.stock.service.IStockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stock")
public class StockController {

    private final IStockService stockService;

    @GetMapping("list")
    public ApiResponse<Object> getStockList(
            @PageableDefault(size = 5) Pageable pageable
    ) {
        Page<StockWithLatestTransactionDto> stockList = stockService.getStockList(pageable);
        return ApiResponse.ok(stockList);
    }

    @GetMapping("{stockId}")
    public ApiResponse<Object> getStock(@PathVariable Long stockId) {
        StockWithTransactionDto stock = stockService.getStock(stockId);
        return ApiResponse.ok(stock);
    }
}
