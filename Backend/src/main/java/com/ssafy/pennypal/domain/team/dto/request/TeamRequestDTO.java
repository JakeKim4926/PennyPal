package com.ssafy.pennypal.domain.team.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamRequestDTO {

    Long teamId;
    Long memberId;

    @Builder
    private TeamRequestDTO(Long teamId, Long memberId) {
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
