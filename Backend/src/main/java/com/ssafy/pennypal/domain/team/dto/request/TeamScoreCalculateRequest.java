package com.ssafy.pennypal.domain.team.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TeamScoreCalculateRequest {

    private Long teamId;

    @Builder
    private TeamScoreCalculateRequest(Long teamId) {
        this.teamId = teamId;
    }
}
