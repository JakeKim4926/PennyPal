package com.ssafy.pennypal.domain.team.dto.response;

import com.ssafy.pennypal.domain.team.dto.request.TeamCreateServiceRequest;
import com.ssafy.pennypal.domain.team.entity.Team;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TeamCreateResponse {

    private String teamName;                                    // 팀 이름

    private Boolean teamIsAutoConfirm;                          // 자동 가입 승인 여부 (true = 자동 / false = 수동)

    private Long teamLeaderId;                                  // 팀장 Id

    private String teamInfo;                                    // 팀 한줄소개

    @Builder
    private TeamCreateResponse(String teamName, Boolean teamIsAutoConfirm, Long teamLeaderId, String teamInfo) {
        this.teamName = teamName;
        this.teamIsAutoConfirm = teamIsAutoConfirm;
        this.teamLeaderId = teamLeaderId;
        this.teamInfo = teamInfo;
    }

    public static TeamCreateResponse of(Team team){
        return TeamCreateResponse.builder()
                .teamName(team.getTeamName())
                .teamIsAutoConfirm(team.getTeamIsAutoConfirm())
                .teamLeaderId(team.getTeamLeaderId())
                .teamInfo(team.getTeamInfo())
                .build();

    }
}
