package com.ssafy.pennypal.domain.team.dto.response;

import com.ssafy.pennypal.domain.member.entity.Member;
import com.ssafy.pennypal.domain.team.entity.Team;
import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamJoinResponse {

    private String teamName;

    private String teamInfo;

    private Integer teamScore;

    private Long teamLeaderId;


    @Builder
    private TeamJoinResponse(String teamName, String teamInfo, Integer teamScore, Long teamLeaderId) {
        this.teamName = teamName;
        this.teamInfo = teamInfo;
        this.teamScore = teamScore;
        this.teamLeaderId = teamLeaderId;
    }
}
