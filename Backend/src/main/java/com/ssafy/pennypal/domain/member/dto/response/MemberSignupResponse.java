package com.ssafy.pennypal.domain.member.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class MemberSignupResponse {
    private int code;
    private HttpStatus status;
    private String message;
}
