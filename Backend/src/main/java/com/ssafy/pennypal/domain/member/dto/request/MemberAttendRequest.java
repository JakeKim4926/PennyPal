package com.ssafy.pennypal.domain.member.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class MemberAttendRequest {
    private Long memberId;
    private LocalDateTime memberDate;
}
