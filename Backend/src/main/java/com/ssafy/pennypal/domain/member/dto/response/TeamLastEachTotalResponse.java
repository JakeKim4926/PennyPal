package com.ssafy.pennypal.domain.member.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TeamLastEachTotalResponse {

    private LocalDate date;
    private Integer totalAmount;

    @Builder
    public TeamLastEachTotalResponse(LocalDate date, Integer totalAmount) {
        this.date = date;
        this.totalAmount = totalAmount;
    }
}
