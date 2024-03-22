package com.ssafy.pennypal.domain.team.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TeamJoinServiceRequest {

    Long teamId;
    Long memberId;

    @Builder
    private TeamJoinServiceRequest(Long teamId, Long memberId) {
        this.teamId = teamId;
        this.memberId = memberId;
    }
}
