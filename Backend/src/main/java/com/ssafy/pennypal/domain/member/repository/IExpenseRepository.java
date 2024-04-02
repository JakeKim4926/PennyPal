package com.ssafy.pennypal.domain.member.repository;

import com.ssafy.pennypal.domain.member.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExpenseRepository extends JpaRepository<Expense, Long> {
}
