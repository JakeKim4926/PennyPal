package com.ssafy.pennypal.domain.market.dto.response;

import com.ssafy.pennypal.domain.market.entity.Product;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO {
    private Long productId;
    private String productName;
    private String productBrand;
    private Integer productPrice;
    private Integer productQuantity;
    private String productCategory;
    private String productImg;

    public ProductResponseDTO(Product product) {
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.productBrand = product.getProductBrand();
        this.productPrice = product.getProductPrice();
        this.productQuantity = product.getProductQuantity();
        this.productCategory = product.getProductCategory();
        this.productImg = product.getProductImg();
    }

}
