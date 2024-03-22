package com.ssafy.pennypal.domain.team.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TeamJoinRequest {

    Long teamId;
    Long memberId;

    @Builder
    private TeamJoinRequest(Long teamId, Long memberId) {
        this.teamId = teamId;
        this.memberId = memberId;
    }

    public TeamJoinServiceRequest toServiceRequest(){
        return TeamJoinServiceRequest.builder()
                .teamId(teamId)
                .memberId(memberId)
                .build();
    }
}
