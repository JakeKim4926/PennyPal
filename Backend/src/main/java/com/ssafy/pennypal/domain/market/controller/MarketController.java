package com.ssafy.pennypal.domain.market.controller;

import com.ssafy.pennypal.domain.market.dto.request.OrderRequestDTO;
import com.ssafy.pennypal.domain.market.dto.response.OrderResponseDTO;
import com.ssafy.pennypal.domain.market.dto.response.ProductResponseDTO;
import com.ssafy.pennypal.domain.market.service.MarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/market")
@RequiredArgsConstructor
public class MarketController {

    private final MarketService marketService;

    // 전체 상품 조회
    @GetMapping("/products")
    public Page<ProductResponseDTO> listAllProducts(Pageable pageable) {
        return marketService.listAllProducts(pageable);
    }

    // 카테고리별 상품 조회
    @GetMapping("/products/category/{category}")
    public Page<ProductResponseDTO> listProductsByCategory(@PathVariable String category, Pageable pageable) {
        return marketService.listProductsByCategory(category, pageable);
    }

    // 키워드 검색
    @GetMapping("/products/search")
    public Page<ProductResponseDTO> searchProducts(@RequestParam("keyword") String keyword, @RequestParam(required = false) String category, Pageable pageable) {
        return marketService.searchProducts(keyword, category, pageable);
    }

    // 상품 상세 조회
    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductResponseDTO> getProductDetails(@PathVariable Long productId) {
        ProductResponseDTO productDetails = marketService.getProductDetails(productId);
        return ResponseEntity.ok(productDetails);
    }


    // 상품 구매
    @PostMapping("/purchase")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody OrderRequestDTO orderRequest) {
        marketService.createOrder(orderRequest);
    }

    // 구매 내역 조회
    @GetMapping("/orders/member/{memberId}")
    public Page<OrderResponseDTO> listOrdersByMemberId(@PathVariable Long memberId, Pageable pageable) {
        return marketService.listOrdersByMemberId(memberId, pageable);
    }
}
