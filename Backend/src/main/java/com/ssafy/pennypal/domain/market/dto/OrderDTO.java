package com.ssafy.pennypal.domain.market.dto;

import com.ssafy.pennypal.domain.market.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {
    private Long memberId;
    private Long productId;
    private Integer buyQuantity;

    public OrderDTO(Order order) {
        this.memberId = order.getMemberId().getMemberId();
        this.productId = order.getProductId().getProductId();
        this.buyQuantity = order.getBuyQuantity();
    }
}
