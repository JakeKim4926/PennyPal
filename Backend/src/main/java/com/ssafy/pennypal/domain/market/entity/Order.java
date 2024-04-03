package com.ssafy.pennypal.domain.market.entity;

import com.ssafy.pennypal.domain.member.entity.Member;
import com.ssafy.pennypal.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;                                   // 주문 Id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;                                    // 주문자

    @ManyToOne(fetch = FetchType.LAZY) // 변경된 부분
    @JoinColumn(name = "product_id")
    private Product product;                                  // 주문 상품

    @Column(name = "buy_quantity")
    private Integer buyQuantity;                                // 주문 수량

    @Column(name = "price_sum")
    private Integer priceSum;                                      // 주문 가격

    @Column(name = "order_date")
    private LocalDateTime orderDate;                            // 주문 날짜 및 시간

    @Builder
    public Order(Member member, Product product, Integer buyQuantity, Integer priceSum, LocalDateTime orderDate) {
        this.member = member;
        this.product = product;
        this.buyQuantity = buyQuantity;
        this.priceSum = priceSum;
        this.orderDate = orderDate;
    }
}
