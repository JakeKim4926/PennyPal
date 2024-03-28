package com.ssafy.pennypal.domain.member.dto.response;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MemberThisTotalExpenses {

    private Integer amount;

    public MemberThisTotalExpenses(int thisWeekTotal) {
        this.amount = thisWeekTotal;
    }
}
