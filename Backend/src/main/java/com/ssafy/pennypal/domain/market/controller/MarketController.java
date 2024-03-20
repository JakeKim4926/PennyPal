package com.ssafy.pennypal.domain.market.controller;

import com.ssafy.pennypal.domain.market.dto.OrderDTO;
import com.ssafy.pennypal.domain.market.dto.ProductDTO;
import com.ssafy.pennypal.domain.market.service.MarketService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/market")
public class MarketController {

    private final MarketService marketService;

    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    // 전체 상품 조회
    @GetMapping("/products")
    public Page<ProductDTO> listAllProducts(Pageable pageable) {
        return marketService.listAllProducts(pageable);
    }

    // 카테고리별 상품 조회
    @GetMapping("/products/category/{category}")
    public Page<ProductDTO> listProductsByCategory(@PathVariable String category, Pageable pageable) {
        return marketService.listProductsByCategory(category, pageable);
    }

    // 키워드 검색
    @GetMapping("/products/search")
    public Page<ProductDTO> searchProducts(@RequestParam("keyword") String keyword, @RequestParam(required = false) String category, Pageable pageable) {
        return marketService.searchProducts(keyword, category, pageable);
    }

    // 상품 구매
    @PostMapping("/purchase")
    public String purchaseProduct(@RequestBody OrderDTO orderRequest) {
        return marketService.purchaseProduct(orderRequest);
    }

    // 구매 내역 조회
    @GetMapping("/orders")
    public Page<OrderDTO> listOrders(Pageable pageable) {
        return marketService.listOrders(pageable);
    }
}
