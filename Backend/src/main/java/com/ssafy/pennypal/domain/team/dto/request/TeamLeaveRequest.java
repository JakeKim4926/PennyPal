package com.ssafy.pennypal.domain.team.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamLeaveRequest {

    private Long teamId;
    private Long memberId;

    @Builder
    private TeamLeaveRequest(Long teamId, Long memberId) {
        this.teamId = teamId;
        this.memberId = memberId;
    }
}
