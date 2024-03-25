package com.ssafy.pennypal.domain.team.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TeamDetailResponse {

    private Long teamId;
    private String teamName;
    private Long teamLeaderId;
    private String teamInfo;
    private Integer teamScore;
    private Integer teamRankNum;

    private List<TeamMemberExpenseResponse> members = new ArrayList<>();

    @Builder
    public TeamDetailResponse(Long teamId, String teamName, Long teamLeaderId, String teamInfo, Integer teamScore, Integer teamRankNum, List<TeamMemberExpenseResponse> members) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamLeaderId = teamLeaderId;
        this.teamInfo = teamInfo;
        this.teamScore = teamScore;
        this.teamRankNum = teamRankNum;
        this.members = members;
    }
}
