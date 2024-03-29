package com.ssafy.pennypal.domain.member.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberUpdatePasswordRequest {
    private Long memberId;
    private String memberOriginPassword;
    private String memberChangePassword;
}
