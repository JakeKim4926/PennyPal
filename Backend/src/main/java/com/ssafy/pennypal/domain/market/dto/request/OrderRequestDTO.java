package com.ssafy.pennypal.domain.market.dto.request;

import com.ssafy.pennypal.domain.market.entity.Order;
import com.ssafy.pennypal.domain.market.entity.Product;
import com.ssafy.pennypal.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequestDTO {
    private Member member;
    private Product product;
    private Integer buyQuantity;

    // 주문 생성
    public Order createOrder(Member member, Product product) {
        return Order.builder()
                .member(member)
                .product(product)
                .buyQuantity(this.buyQuantity)
                .priceSum(this.product.getProductPrice() * this.buyQuantity)
                .orderDate(LocalDateTime.now())
                .build();
    }
}
