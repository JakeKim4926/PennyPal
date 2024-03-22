package com.ssafy.pennypal.domain.market.entity;

import com.querydsl.core.annotations.QueryProjection;
import com.ssafy.pennypal.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
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

    @Column(name = "product_brand")
    private String productBrand;                                // 상품 브랜드

    @Column(name = "product_price")
    private Integer productPrice;                               // 상품 가격

    @Column(name = "product_quantity")
    private Integer productQuantity;                            // 상품 수량

    @Column(name = "product_category")
    private String productCategory;                             // 상품 카테고리

    @Builder
    @QueryProjection
    public Product(String productImg, String productName, String productBrand, Integer productPrice, Integer productQuantity, String productCategory) {
        this.productImg = productImg;
        this.productName = productName;
        this.productBrand = productBrand;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productCategory = productCategory;
    }
}
