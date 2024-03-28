package com.ssafy.pennypal.domain.team.dto.response;

import com.ssafy.pennypal.domain.team.entity.Team;
import com.ssafy.pennypal.domain.team.entity.TeamRankHistory;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class TeamRankHistoryResponse {

    private String teamName;
    private LocalDate rankDate;
    private Integer rankNum;
    private Integer teamScore;


    @Builder
    public TeamRankHistoryResponse(String teamName, LocalDate rankDate, Integer rankNum, Integer teamScore) {
        this.teamName = teamName;
        this.rankDate = rankDate;
        this.rankNum = rankNum;
        this.teamScore = teamScore;
    }

    public static TeamRankHistoryResponse of(TeamRankHistory history) {
        return TeamRankHistoryResponse.builder()
                .teamName(history.getTeam().getTeamName())
                .rankDate(history.getRankDate())
                .rankNum(history.getRankNum())
                .teamScore(history.getTeam().getTeamScore())
                .build();
    }
}