package com.ssafy.pennypal.bank.repository;

import com.ssafy.pennypal.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IBankRepository extends JpaRepository<Member, Long>, QuerydslPredicateExecutor<Member>, IBankRepositoryCustom {
}
