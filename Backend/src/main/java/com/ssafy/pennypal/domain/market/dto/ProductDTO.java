package com.ssafy.pennypal.domain.market.dto;

import com.ssafy.pennypal.domain.market.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private Long productId;
    private String productName;
    private Integer productPrice;
    private Integer productQuantity;
    private String productCategory;
    private String productImg;

    public ProductDTO(Product product) {
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.productPrice = product.getProductPrice();
        this.productQuantity = product.getProductQuantity();
        this.productCategory = product.getProductCategory();
        this.productImg = product.getProductImg();
    }
}
