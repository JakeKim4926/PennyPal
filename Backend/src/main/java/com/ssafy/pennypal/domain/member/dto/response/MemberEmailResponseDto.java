package com.ssafy.pennypal.domain.member.dto.response;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEmailResponseDto {

    private Long memberId;
    private String memberEmail;

    @Builder
    public MemberEmailResponseDto(Long memberId, String memberEmail) {
        this.memberId = memberId;
        this.memberEmail = memberEmail;
    }
}
