package com.ssafy.pennypal.domain.member.dto.response;


import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MemberLastTotalExpenses {

    private Integer amount;

    public MemberLastTotalExpenses(Integer lastWeekExpenses) {
        this.amount = lastWeekExpenses;
    }

}
