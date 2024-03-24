package com.ssafy.pennypal.domain.member.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Getter
public class MemberExpensesDetailResponse {

    private LocalDate expenseDate;
    private Integer expenseAmount;

    @Builder
    public MemberExpensesDetailResponse(LocalDate expenseDate, Integer expenseAmount) {
        this.expenseDate = expenseDate;
        this.expenseAmount = expenseAmount;
    }
}
