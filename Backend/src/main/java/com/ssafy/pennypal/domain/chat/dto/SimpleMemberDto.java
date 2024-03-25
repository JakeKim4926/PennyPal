package com.ssafy.pennypal.domain.chat.dto;

import com.ssafy.pennypal.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SimpleMemberDto {

    private Long memberId;
    private String memberNickname;

    @Builder
    private SimpleMemberDto(Long memberId, String memberNickname) {
        this.memberId = memberId;
        this.memberNickname = memberNickname;
    }

    public static SimpleMemberDto convertToSimpleMemberDto(Member member) {
        return SimpleMemberDto.builder()
                .memberId(member.getMemberId())
                .memberNickname(member.getMemberNickname())
                .build();
    }
}
