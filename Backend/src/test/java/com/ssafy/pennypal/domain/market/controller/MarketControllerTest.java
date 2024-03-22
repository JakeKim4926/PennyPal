//package com.ssafy.pennypal.domain.market.controller;
//
//import com.ssafy.pennypal.common.RestDocsSupport;
//import com.ssafy.pennypal.domain.market.dto.request.OrderRequestDTO;
//import com.ssafy.pennypal.domain.market.entity.Product;
//import com.ssafy.pennypal.domain.market.repository.IOrderRepository;
//import com.ssafy.pennypal.domain.market.repository.IProductRepository;
//import com.ssafy.pennypal.domain.member.entity.Member;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDateTime;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mock;
//
//class MarketControllerTest extends RestDocsSupport {
//
//    @Override
//    protected Object initController() {
//        return null;
//    }
//
//    @DisplayName("전체 상품 조회")
//    @Test
//    void listAllProducts() throws Exception {
//        // given
//
//        // when
//
//        // then
//    }
//
//    @Test
//    void listProductsByCategory() {
//        // given
//
//        // when
//
//        // then
//    }
//
//    @Test
//    void searchProducts() {
//        // given
//
//        // when
//
//        // then
//    }
//
//    @Test
//    void getProductDetails() {
//        // given
//
//        // when
//
//        // then
//    }
//
//    @Test
//    void createOrder() throws Exception {
//        // given
//        OrderRequestDTO orderRequest = OrderRequestDTO.builder()
//                .member(Member.builder()
//                        .memberEmail("xion2664@gmail.com")
//                        .build())
//                .product(Product.builder()
//                        .productName("바나나맛 우유")
//                        .build())
//                .build();
//
//        // when
//
//        // then
//    }
//
//    @Test
//    void listOrdersByMemberId() {
//        // given
//
//        // when
//
//        // then
//    }
//}