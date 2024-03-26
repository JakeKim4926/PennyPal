package com.ssafy.pennypal.domain.team.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamBanishRequest {

    private Long teamId;
    private Long teamLeaderId;
    private Long targetMemberId;

}
