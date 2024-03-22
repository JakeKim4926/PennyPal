package com.ssafy.pennypal.domain.market.repository;

import com.ssafy.pennypal.domain.market.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {

    // 사용자별 구매 내역 조회
    Page<Order> findByMemberMemberId(Long memberId, Pageable pageable);
}