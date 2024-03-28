package com.ssafy.pennypal.domain.team.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TeamWaitingListResponse {

    private Long memberId;
    private String memberNickname;
    private String memberMostCategory;

    @Builder
    public TeamWaitingListResponse(Long memberId,String memberNickname, String memberMostCategory) {
        this.memberId = memberId;
        this.memberNickname = memberNickname;
        this.memberMostCategory = memberMostCategory;
    }
}
