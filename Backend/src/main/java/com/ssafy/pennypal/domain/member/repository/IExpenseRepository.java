package com.ssafy.pennypal.domain.member.repository;

import com.ssafy.pennypal.domain.member.entity.Expense;
import com.ssafy.pennypal.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByMember(Member member);
}
