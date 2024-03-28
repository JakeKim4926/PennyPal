package com.ssafy.pennypal.domain.team.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamRankWeeklyResponse {

    private List<TeamRankHistoryResponse> teamRanks;

    private String myTeamName;
    private Integer myTeamRankNum;
    private Integer myTeamScore;

    @Builder
    public TeamRankWeeklyResponse(List<TeamRankHistoryResponse> teamRanks,
                                  String myTeamName, Integer myTeamRankNum, Integer myTeamScore) {
        this.teamRanks = teamRanks;
        this.myTeamName = myTeamName;
        this.myTeamRankNum = myTeamRankNum;
        this.myTeamScore = myTeamScore;
    }
}
