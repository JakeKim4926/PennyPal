package com.ssafy.pennypal.domain.member.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberLoginRequest {
    private String memberEmail;
    private String memberPassword;
}
