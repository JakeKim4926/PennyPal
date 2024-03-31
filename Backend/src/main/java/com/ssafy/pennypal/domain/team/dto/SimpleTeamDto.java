package com.ssafy.pennypal.domain.team.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SimpleTeamDto {

    private Long teamId;
    private Long memberId;

    @Builder
    private SimpleTeamDto(Long teamId, Long memberId) {
        this.teamId = teamId;
        this.memberId = memberId;
    }
}
