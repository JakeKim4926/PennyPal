package com.ssafy.pennypal.domain.team.dto.response;

import com.ssafy.pennypal.domain.member.entity.Member;
import com.ssafy.pennypal.domain.team.entity.Team;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class TeamJoinResponse {

    private String teamName;

    private String teamInfo;

    private Integer teamScore;

    private Long teamLeaderId;

    private List<TeamMemberDetailResponse> members;

    @Builder
    private TeamJoinResponse(String teamName, String teamInfo, Integer teamScore, Long teamLeaderId, List<TeamMemberDetailResponse> members) {
        this.teamName = teamName;
        this.teamInfo = teamInfo;
        this.teamScore = teamScore;
        this.teamLeaderId = teamLeaderId;
        this.members = members;
    }
}
