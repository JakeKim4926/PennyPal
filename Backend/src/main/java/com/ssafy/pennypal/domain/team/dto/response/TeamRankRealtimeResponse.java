package com.ssafy.pennypal.domain.team.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class TeamRankRealtimeResponse {

    private String teamName;
    private Integer teamRankRealtime;
    private Integer teamScoreRealtime;

    @Builder
    public TeamRankRealtimeResponse(String teamName, Integer teamRankRealtime, Integer teamScoreRealtime) {
        this.teamName = teamName;
        this.teamRankRealtime = teamRankRealtime;
        this.teamScoreRealtime = teamScoreRealtime;
    }
}
