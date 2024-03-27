package com.ssafy.pennypal.domain.member.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberUpdateNicknameRequest {
    private Long memberId;
    private String memberNickname;
}
