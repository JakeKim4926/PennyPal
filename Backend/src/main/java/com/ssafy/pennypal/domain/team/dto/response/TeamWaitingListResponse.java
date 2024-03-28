package com.ssafy.pennypal.domain.team.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
