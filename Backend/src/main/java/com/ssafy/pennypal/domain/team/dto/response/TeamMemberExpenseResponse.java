package com.ssafy.pennypal.domain.team.dto.response;

import com.ssafy.pennypal.domain.member.dto.response.MemberExpensesDetailResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TeamMemberExpenseResponse {

    private String memberNickname;

    private List<MemberExpensesDetailResponse> memberLastWeekExpenses = new ArrayList<>();
    private List<MemberExpensesDetailResponse> memberThisWeekExpenses = new ArrayList<>();

    @Builder
    public TeamMemberExpenseResponse(String memberNickname,
                                     List<MemberExpensesDetailResponse> memberLastWeekExpenses,
                                     List<MemberExpensesDetailResponse> memberThisWeekExpenses
    ) {
        this.memberNickname = memberNickname;
        this.memberLastWeekExpenses = memberLastWeekExpenses;
        this.memberThisWeekExpenses = memberThisWeekExpenses;
    }

}
