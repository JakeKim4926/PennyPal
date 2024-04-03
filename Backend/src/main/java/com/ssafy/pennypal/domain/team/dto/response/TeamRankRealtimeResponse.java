package com.ssafy.pennypal.domain.team.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
