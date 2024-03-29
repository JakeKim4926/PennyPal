package com.ssafy.pennypal.domain.team.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamOtherDetailResponse {

    private String teamName;
    private String teamInfo;
    private String teamLeaderNickname;
    private Integer lastRank;

    @Builder
    private TeamOtherDetailResponse(String teamName, String teamInfo, String teamLeaderNickname, Integer lastRank) {
        this.teamName = teamName;
        this.teamInfo = teamInfo;
        this.teamLeaderNickname = teamLeaderNickname;
        this.lastRank = lastRank;
    }
}
