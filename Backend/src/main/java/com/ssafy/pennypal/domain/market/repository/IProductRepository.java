package com.ssafy.pennypal.domain.market.repository;

import com.ssafy.pennypal.domain.market.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

    // 카테고리별 상품 조회
    Page<Product> findByProductCategory(String category, Pageable pageable);

    // 상품명 또는 브랜드를 기준으로 검색
//    Page<Product> findByProductNameContainingOrProductBrandContaining(String productName, String productBrand, Pageable pageable);
//
//    // 상품명과 카테고리를 기준으로 검색
//    Page<Product> findByProductNameContainingAndProductCategory(String productName, String category, Pageable pageable);
}
