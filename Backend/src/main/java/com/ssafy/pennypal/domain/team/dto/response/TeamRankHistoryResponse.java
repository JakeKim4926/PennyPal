package com.ssafy.pennypal.domain.team.dto.response;

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

    @Builder
    public TeamRankHistoryResponse(String teamName, LocalDate rankDate, Integer rankNum) {
        this.teamName = teamName;
        this.rankDate = rankDate;
        this.rankNum = rankNum;
    }
}
