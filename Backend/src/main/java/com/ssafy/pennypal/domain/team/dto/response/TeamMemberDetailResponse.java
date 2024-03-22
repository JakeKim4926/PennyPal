package com.ssafy.pennypal.domain.team.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TeamMemberDetailResponse {

    private String memberNickname;

    @Builder
    public TeamMemberDetailResponse(String memberNickname) {

        this.memberNickname = memberNickname;
    }
}
