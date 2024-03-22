package com.ssafy.pennypal.domain.team.dto.request;

import com.ssafy.pennypal.domain.team.entity.Team;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TeamCreateRequest {

    @NotBlank(message = "팀 이름은 필수입니다.")
    private String teamName;                                    // 팀 이름

    private Boolean teamIsAutoConfirm;                          // 자동 가입 승인 여부 (true = 자동 / false = 수동)

    @Positive(message = "사용자 id는 0 이상이어야 합니다.")
    private Long teamLeaderId;                                  // 팀장 Id

    @NotNull(message = "팀 한줄소개는 필수입니다.")
    private String teamInfo;                                    // 팀 한줄소개

    @Builder
    private TeamCreateRequest(String teamName, Boolean teamIsAutoConfirm, Long teamLeaderId, String teamInfo) {
        this.teamName = teamName;
        this.teamIsAutoConfirm = teamIsAutoConfirm;
        this.teamLeaderId = teamLeaderId;
        this.teamInfo = teamInfo;
    }

    public TeamCreateServiceRequest toServiceRequest(){
        return TeamCreateServiceRequest.builder()
                .teamName(teamName)
                .teamIsAutoConfirm(teamIsAutoConfirm)
                .teamLeaderId(teamLeaderId)
                .teamInfo(teamInfo)
                .build();
    }
}
