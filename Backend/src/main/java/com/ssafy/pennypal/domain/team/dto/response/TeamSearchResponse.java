package com.ssafy.pennypal.domain.team.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TeamSearchResponse {

    private Long teamId;
    private String teamName;
    private Integer teamMembersNum;
    private String teamLeaderNickname;
    private Boolean teamIsAutoConfirm;
    private String teamInfo;

    @Builder
    public TeamSearchResponse(Long teamId, String teamName, Integer teamMembersNum, String teamLeaderNickname,
                              Boolean teamIsAutoConfirm, String teamInfo) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamMembersNum = teamMembersNum;
        this.teamLeaderNickname = teamLeaderNickname;
        this.teamIsAutoConfirm = teamIsAutoConfirm;
        this.teamInfo = teamInfo;
    }
}
