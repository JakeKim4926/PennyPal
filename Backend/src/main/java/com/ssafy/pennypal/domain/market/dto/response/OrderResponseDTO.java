package com.ssafy.pennypal.domain.market.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.pennypal.domain.market.entity.Order;
import com.ssafy.pennypal.domain.member.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDTO {
    private Long orderId;
    private Long productId;
    private Integer buyQuantity;
    private Integer priceSum;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime orderDate;

    public OrderResponseDTO(Order order) {
        this.orderId = order.getOrderId();
        this.productId = order.getProduct().getProductId();
        this.buyQuantity = order.getBuyQuantity();
        this.priceSum = order.getPriceSum();
        this.orderDate = order.getOrderDate();
    }
}
