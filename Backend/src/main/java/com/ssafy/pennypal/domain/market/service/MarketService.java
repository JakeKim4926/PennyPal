package com.ssafy.pennypal.domain.market.service;

import com.ssafy.pennypal.domain.market.dto.request.OrderRequestDTO;
import com.ssafy.pennypal.domain.market.dto.response.OrderResponseDTO;
import com.ssafy.pennypal.domain.market.dto.response.ProductResponseDTO;
import com.ssafy.pennypal.domain.market.entity.Order;
import com.ssafy.pennypal.domain.market.entity.Product;
import com.ssafy.pennypal.domain.market.repository.IOrderRepository;
import com.ssafy.pennypal.domain.market.repository.IProductRepository;
import com.ssafy.pennypal.domain.member.entity.Member;
import com.ssafy.pennypal.domain.member.repository.IMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MarketService {

    private final IProductRepository productRepository;
    private final IOrderRepository orderRepository;
    private final IMemberRepository memberRepository;

    // 전체 상품 조회
    public Page<ProductResponseDTO> listAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable).map(ProductResponseDTO::new);
    }

    // 카테고리별 조회
    public Page<ProductResponseDTO> listProductsByCategory(String category, Pageable pageable) {
        return productRepository.findByProductCategory(category, pageable).map(ProductResponseDTO::new);
    }

    // 상품 검색
    public Page<ProductResponseDTO> searchProducts(String keyword, Pageable pageable) {
        return productRepository.findByProductNameContainingOrProductBrandContaining(keyword, keyword, pageable)
                .map(ProductResponseDTO::new);
    }


    // 상품 상세 조회
    public ProductResponseDTO getProductDetails(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        return new ProductResponseDTO(product);
    }

    // 상품 구매
    @Transactional
    public void createOrder(OrderRequestDTO orderRequest) {
        // 사용자 확인
        Member member = memberRepository.findById(orderRequest.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        // 상품 확인
        Product product = productRepository.findById(orderRequest.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        Order order = Order.builder()
                .member(member)
                .product(product)
                .buyQuantity(orderRequest.getBuyQuantity())
                .priceSum(product.getProductPrice() * orderRequest.getBuyQuantity())
                .orderDate(LocalDateTime.now())
                .build();

        // 주문 총액 계산
        Integer priceSum = product.getProductPrice() * orderRequest.getBuyQuantity();

        // 사용자의 보유 포인트 확인
        if (member.getMemberPoint() < priceSum)
            throw new IllegalArgumentException("Not enough points");
        else {
            // 포인트 차감
            member.setMemberPoint(member.getMemberPoint() - priceSum);

            // 주문 생성 및 저장
//            Order order = orderRequest.createOrder(member, product);
            orderRepository.save(order);
        }
    }

    // 사용자 구매 내역 조회
    public Page<OrderResponseDTO> listOrdersByMemberId(Long memberId, Pageable pageable) {
        Page<Order> orders = orderRepository.findByMemberMemberId(memberId, pageable);
        return orders.map(OrderResponseDTO::new);
    }

    // 회원 포인트 조회 메서드
    public int getMemberPoints(Long memberId) {
        // memberId를 이용하여 회원 정보를 조회합니다.
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원을 찾을 수 없습니다."));

        // 회원의 포인트를 반환합니다.
        return member.getMemberPoint();
    }
}
