package com.ssafy.pennypal.domain.team.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamRankRealtimeResponse {

    List<TeamRankRealtimeResponse> teamRanks;

    private String teamName;
    private Integer teamRankRealtime;
    private Integer teamScoreRealtime;

    @Builder
    public TeamRankRealtimeResponse(List<TeamRankRealtimeResponse> teamRanks, String teamName,
                                    Integer teamRankRealtime, Integer teamScoreRealtime) {
        this.teamRanks = teamRanks;
        this.teamName = teamName;
        this.teamRankRealtime = teamRankRealtime;
        this.teamScoreRealtime = teamScoreRealtime;
    }
}
