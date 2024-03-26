package com.ssafy.pennypal.domain.member.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberSignupRequest {
    private String memberEmail;                                                 // 사용자 이메일
    private String memberPassword;                                              // 사용자 비밀번호
    private String memberName;                                                  // 사용자 이름
    private String memberNickname;                                              // 사용자 닉네임
    private LocalDateTime memberBirthDate;                                      // 사용자 생년월일(YYYY-MM-DD)
}
