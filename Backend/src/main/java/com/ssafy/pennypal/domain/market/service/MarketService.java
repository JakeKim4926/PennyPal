package com.ssafy.pennypal.domain.market.service;

import com.ssafy.pennypal.domain.market.dto.OrderDTO;
import com.ssafy.pennypal.domain.market.dto.ProductDTO;
import com.ssafy.pennypal.domain.market.repository.IOrderRepository;
import com.ssafy.pennypal.domain.market.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MarketService {

    private final IProductRepository productRepository;
    private final IOrderRepository orderRepository;

    public Page<ProductDTO> listAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable).map(ProductDTO::new);
    }

    public Page<ProductDTO> listProductsByCategory(String category, Pageable pageable) {
        return productRepository.findByProductCategory(category, pageable).map(ProductDTO::new);
    }

//    public Page<ProductDTO> searchProducts(String keyword, String category, Pageable pageable) {
//        if (category != null && !category.isEmpty()) {
//            return productRepository.findByProductNameContainingAndProductCategory(keyword, category, pageable).map(ProductDTO::new);
//        } else {
//            return productRepository.findByProductNameContainingOrProductBrandContaining(keyword, keyword, pageable).map(ProductDTO::new);
//        }
//    }

    @Transactional
    public String purchaseProduct(OrderDTO orderRequest) {
        // 상품 조회, 재고 확인, 주문 생성 등 구현 필요
        // 예시 코드이므로 실제 구현에는 주문 로직이 포함되어야 함
        return "Order processed";
    }

    public Page<OrderDTO> listOrders(Pageable pageable) {
        return orderRepository.findAll(pageable).map(OrderDTO::new);
    }
}
