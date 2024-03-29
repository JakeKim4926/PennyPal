package com.ssafy.pennypal.domain.team.dto.response;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamRankCalculateResponse {

    private Long teamId;
    private String teamName;
    private Integer teamScore;

    @Setter
    private Integer teamRankNum;

    @Builder
    public TeamRankCalculateResponse(Long teamId, String teamName, Integer teamScore, Integer teamRankNum) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamScore = teamScore;
        this.teamRankNum = teamRankNum;
    }
}
