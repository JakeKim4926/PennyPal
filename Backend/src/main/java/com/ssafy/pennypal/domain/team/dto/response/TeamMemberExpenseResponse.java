package com.ssafy.pennypal.domain.team.dto.response;

import com.ssafy.pennypal.domain.member.dto.response.MemberExpensesDetailResponse;
import com.ssafy.pennypal.domain.member.dto.response.MemberLastTotalExpenses;
import com.ssafy.pennypal.domain.member.dto.response.MemberThisTotalExpenses;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TeamMemberExpenseResponse {

    private Long memberId;
    private String memberNickname;
    private Integer memberLastTotalExpenses; // 팀원 별 지난주 지출 총액
    private Integer memberThisTotalExpenses; // 팀원 별 이번주 지출 총액

    @Builder
    public TeamMemberExpenseResponse(Long memberId,
                                     String memberNickname,
                                     Integer memberLastTotalExpenses,
                                     Integer memberThisTotalExpenses
    ) {
        this.memberId = memberId;
        this.memberNickname = memberNickname;
        this.memberLastTotalExpenses = memberLastTotalExpenses;
        this.memberThisTotalExpenses = memberThisTotalExpenses;
    }

}
