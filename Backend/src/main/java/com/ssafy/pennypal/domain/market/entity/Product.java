package com.ssafy.pennypal.domain.market.entity;

import com.ssafy.pennypal.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;                                     // 상품 Id

    @Column(name = "product_img")
    private String productImg;                                  // 상품 이미지

    @Column(name = "product_name")
    private String productName;                                 // 상품 이름

    @Column(name = "product_price")
    private Integer productPrice;                               // 상품 가격

    @Column(name = "product_quantity")
    private Integer productQuantity;                            // 상품 수량
}
