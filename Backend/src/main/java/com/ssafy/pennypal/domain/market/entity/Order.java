package com.ssafy.pennypal.domain.market.entity;

import com.ssafy.pennypal.domain.member.entity.Member;
import com.ssafy.pennypal.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;                                   // 주문 Id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member memberId;                                    // 주문자

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REMOVE}
    )
    @JoinColumn(name = "product_id")
    private Product productId;                                  // 주문 상품

    @Column(name = "buy_quantity")
    private Integer buyQuantity;                                // 주문 수량

    @Column(name = "product_price")
    private Integer price;                                      // 주문 가격

    @Column(name = "order_date")
    private LocalDateTime orderDate;                            // 주문 날짜 및 시간
}
