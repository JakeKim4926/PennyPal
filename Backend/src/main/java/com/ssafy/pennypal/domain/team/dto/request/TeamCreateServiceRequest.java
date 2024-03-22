package com.ssafy.pennypal.domain.team.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TeamCreateServiceRequest {

    private String teamName;                                    // 팀 이름

    private Boolean teamIsAutoConfirm;                          // 자동 가입 승인 여부 (true = 자동 / false = 수동)

    private Long teamLeaderId;                                  // 팀장 Id

    private String teamInfo;                                    // 팀 한줄소개

    @Builder
    private TeamCreateServiceRequest(String teamName, Boolean teamIsAutoConfirm, Long teamLeaderId, String teamInfo) {
        this.teamName = teamName;
        this.teamIsAutoConfirm = teamIsAutoConfirm;
        this.teamLeaderId = teamLeaderId;
        this.teamInfo = teamInfo;
    }
}
