package com.ssafy.pennypal.domain.team.dto.response;

import com.ssafy.pennypal.domain.member.dto.response.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TeamDetailResponse {

    private Long teamId;
    private String teamName;
    private Long teamLeaderId;
    private String teamInfo;
    private Integer teamScore;
    private Integer teamRankRealtime;

    private Integer teamLastTotalExpenses; // 팀 지난주 지출 총액
    private Integer teamThisTotalExpenses; // 팀 이번주 지출 총액
    private List<TeamLastEachTotalResponse> teamLastEachTotalExpenses; // 팀 지난주 일자별 지출 총액
    private List<TeamThisEachTotalResponse> teamThisEachTotalExpenses; // 팀 이번주 일자별 지출 총액
    private List<TeamMemberExpenseResponse> members = new ArrayList<>(); // 팀원

    @Builder
    public TeamDetailResponse(Long teamId, String teamName, Long teamLeaderId, String teamInfo, Integer teamScore,
                              Integer teamRankRealtime, Integer teamLastTotalExpenses, Integer teamThisTotalExpenses,
                              List<TeamLastEachTotalResponse> teamLastEachTotalExpenses,
                              List<TeamThisEachTotalResponse> teamThisEachTotalExpenses,
                              List<TeamMemberExpenseResponse> members) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamLeaderId = teamLeaderId;
        this.teamInfo = teamInfo;
        this.teamScore = teamScore;
        this.teamRankRealtime = teamRankRealtime;
        this.teamLastTotalExpenses = teamLastTotalExpenses;
        this.teamThisTotalExpenses = teamThisTotalExpenses;
        this.teamLastEachTotalExpenses = teamLastEachTotalExpenses;
        this.teamThisEachTotalExpenses = teamThisEachTotalExpenses;
        this.members = members;
    }
}
