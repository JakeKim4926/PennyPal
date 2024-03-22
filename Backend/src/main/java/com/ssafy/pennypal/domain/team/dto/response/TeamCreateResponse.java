package com.ssafy.pennypal.domain.team.dto.response;

import com.ssafy.pennypal.domain.team.dto.request.TeamCreateServiceRequest;
import com.ssafy.pennypal.domain.team.entity.Team;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class TeamCreateResponse {

    private String teamName;

    private String teamInfo;

    private Integer teamScore;

    private Long teamLeaderId;

    private List<TeamMemberDetailResponse> members;

    @Builder
    public TeamCreateResponse(String teamName, String teamInfo, Integer teamScore, Long teamLeaderId, List<TeamMemberDetailResponse> members) {
        this.teamName = teamName;
        this.teamInfo = teamInfo;
        this.teamScore = teamScore;
        this.teamLeaderId = teamLeaderId;
        this.members = members;
    }

    public static TeamCreateResponse of(Team team, List<TeamMemberDetailResponse> memberDetails){
        return TeamCreateResponse.builder()
                .teamName(team.getTeamName())
                .teamInfo(team.getTeamInfo())
                .teamScore(team.getTeamScore())
                .teamLeaderId(team.getTeamLeaderId())
                .members(memberDetails)
                .build();

    }
}
