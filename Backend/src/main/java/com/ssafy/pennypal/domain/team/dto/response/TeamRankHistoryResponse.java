package com.ssafy.pennypal.domain.team.dto.response;

import com.ssafy.pennypal.domain.team.entity.Team;
import com.ssafy.pennypal.domain.team.entity.TeamRankHistory;
import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamRankHistoryResponse {

    private String teamName;
    private LocalDate rankDate;
    private Integer rankNum;
    private Integer teamScore;
    private Integer rewardPoint;

    @Builder
    public TeamRankHistoryResponse(String teamName, LocalDate rankDate, Integer rankNum, Integer teamScore, Integer rewardPoint) {
        this.teamName = teamName;
        this.rankDate = rankDate;
        this.rankNum = rankNum;
        this.teamScore = teamScore;
        this.rewardPoint = rewardPoint;
    }

    public static TeamRankHistoryResponse of(TeamRankHistory history) {
        return TeamRankHistoryResponse.builder()
                .teamName(history.getTeam().getTeamName())
                .rankDate(history.getRankDate())
                .rankNum(history.getRankNum())
                .teamScore(history.getTeam().getTeamScore())
                .rewardPoint(history.getRewardPoint())
                .build();
    }
}