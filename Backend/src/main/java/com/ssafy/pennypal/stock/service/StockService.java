package com.ssafy.pennypal.stock.service;

import com.ssafy.pennypal.stock.entity.Stock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class StockService implements IStockService {

    @Override
    public List<Stock> getStockList() {
        return null;
    }
}
