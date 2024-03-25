package com.ssafy.pennypal.domain.market.controller;

import com.ssafy.pennypal.common.RestDocsSupport;
import com.ssafy.pennypal.domain.market.dto.request.OrderRequestDTO;
import com.ssafy.pennypal.domain.market.dto.response.ProductResponseDTO;
import com.ssafy.pennypal.domain.market.entity.Product;
import com.ssafy.pennypal.domain.market.service.MarketService;
import com.ssafy.pennypal.domain.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MarketControllerTest extends RestDocsSupport {

    @Override
    protected Object initController() {
        return new MarketController(marketService);
    }

    private final MarketService marketService = mock(MarketService.class);

    @DisplayName("전체 상품 조회")
    @Test
    void listAllProducts() throws Exception {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        given(marketService.listAllProducts(pageable)).willReturn(new PageImpl<>(Collections.emptyList()));

        // When & Then
        mockMvc.perform(get("/api/market/products").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("products-list",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                responseFields(
                                        fieldWithPath("[]").description("A list of products"),
                                        subsectionWithPath("[].productId").type(JsonFieldType.NUMBER).description("상품 ID"),
                                        subsectionWithPath("[].productName").type(JsonFieldType.STRING).description("상품명"),
                                        subsectionWithPath("[].productPrice").type(JsonFieldType.NUMBER).description("상품 가격"),
                                        subsectionWithPath("[].productBrand").type(JsonFieldType.STRING).description("상품 브랜드"),
                                        subsectionWithPath("[].productImg").type(JsonFieldType.STRING).description("상품 이미지 URL"),
                                        subsectionWithPath("[].productCategory").type(JsonFieldType.STRING).description("상품 카테고리")
                                )
                        )
                );
    }

//    @DisplayName("카테고리별 상품 조회")
//    @Test
//    void listProductsByCategory() throws Exception {
//        // Given
//        String category = "Beverages";
//        Pageable pageable = PageRequest.of(0, 10);
//        given(marketService.listProductsByCategory(category, pageable)).willReturn(new PageImpl<>(Collections.emptyList()));
//
//        // When & Then
//        mockMvc.perform(get("/api/market/products/category/" + category).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @DisplayName("상품 검색")
//    @Test
//    void searchProducts() throws Exception {
//        // Given
//        String keyword = "바나나맛 우유";
//        Pageable pageable = PageRequest.of(0, 10);
//        given(marketService.searchProducts(keyword, pageable)).willReturn(new PageImpl<>(Collections.emptyList()));
//
//        // When & Then
//        mockMvc.perform(get("/api/market/products/search").param("keyword", keyword).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @DisplayName("상품 상세 조회")
//    @Test
//    void getProductDetails() throws Exception {
//        // Given
//        Long productId = 1L;
//        given(marketService.getProductDetails(productId)).willReturn(new ProductResponseDTO()); // ProductResponseDTO의 예시 인스턴스 필요
//
//        // When & Then
//        mockMvc.perform(get("/api/market/products/" + productId).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @DisplayName("주문 생성")
//    @Test
//    void createOrder() throws Exception {
//        // Given
//        OrderRequestDTO orderRequest = new OrderRequestDTO(); // 적절한 생성자 또는 setter를 사용하여 초기화
//        String jsonOrderRequest = // Jackson ObjectMapper를 사용하여 orderRequest를 JSON 문자열로 변환
//
////        OrderRequestDTO orderRequest = OrderRequestDTO.builder()
////                .member(Member.builder()
////                        .memberEmail("xion2664@gmail.com")
////                        .build())
////                .product(Product.builder()
////                        .productPrice(1700)
////                        .build())
////                .buyQuantity(3)
////                .build();
//    }
//
//    @DisplayName("구매 내역 조회")
//    @Test
//    void listOrdersByMemberId() throws Exception {
//        // given
//
//        // when
//
//        // then
//    }
}