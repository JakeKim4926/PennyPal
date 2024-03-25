package com.ssafy.pennypal.domain.team.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamModifyResponse {

    private String teamName;

    private Long teamLeaderId;

    private Boolean teamIsAutoConfirm;

    private String teamInfo;

    @Builder
    private TeamModifyResponse(String teamName, Long teamLeaderId, Boolean teamIsAutoConfirm, String teamInfo) {
        this.teamName = teamName;
        this.teamLeaderId = teamLeaderId;
        this.teamIsAutoConfirm = teamIsAutoConfirm;
        this.teamInfo = teamInfo;
    }
}
