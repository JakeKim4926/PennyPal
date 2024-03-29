package com.ssafy.pennypal.domain.member.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TeamThisEachTotalResponse {

    private LocalDate date;
    private Integer totalAmount;

    @Builder
    public TeamThisEachTotalResponse(LocalDate date, Integer totalAmount) {
        this.date = date;
        this.totalAmount = totalAmount;
    }
}
