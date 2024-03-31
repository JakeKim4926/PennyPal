package com.ssafy.pennypal.domain.member.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberLoginResponse {
    private Long memberId;
    private String memberNickname;
    private String memberToken;
}
