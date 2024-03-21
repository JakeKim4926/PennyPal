package com.ssafy.pennypal.domain.team.dto.response;

import com.ssafy.pennypal.domain.member.entity.Member;
import com.ssafy.pennypal.domain.team.entity.Team;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class TeamJoinResponse {

    private String teamName;                                    // 팀 이름

    private Integer teamScore;                                  // 팀 점수

    private List<Member> members;                               // 팀 구성원

    @Builder
    private TeamJoinResponse(String teamName, Integer teamScore, List<Member> members) {
        this.teamName = teamName;
        this.teamScore = teamScore;
        this.members = members;
    }

    public static TeamJoinResponse of(Team team){
        return TeamJoinResponse.builder()
                .teamName(team.getTeamName())
                .teamScore(team.getTeamScore())
                .members(team.getMembers())
                .build();
    }
}
