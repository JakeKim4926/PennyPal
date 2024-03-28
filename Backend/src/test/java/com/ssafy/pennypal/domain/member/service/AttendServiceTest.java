package com.ssafy.pennypal.domain.member.service;

import com.ssafy.pennypal.domain.member.dto.request.MemberAttendRequest;
import com.ssafy.pennypal.domain.member.dto.request.MemberLoginRequest;
import com.ssafy.pennypal.domain.member.dto.request.MemberSignupRequest;
import com.ssafy.pennypal.domain.member.dto.response.MemberLoginResponse;
import com.ssafy.pennypal.domain.member.entity.Attend;
import com.ssafy.pennypal.domain.member.entity.Member;
import com.ssafy.pennypal.domain.member.repository.IAttendRepository;
import com.ssafy.pennypal.domain.member.repository.IMemberRepository;
import com.ssafy.pennypal.global.common.api.ApiResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class AttendServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private AttendService attendService;
    
    @Autowired
    private IMemberRepository memberRepository;

    @Autowired
    private IAttendRepository attendRepository;

    @DisplayName("저장된 날짜와 하루 이상 차이날 경우 출석 여부를 true로 설정 하고, 주간 출석 횟수를 1증가한다.")
    @Test
    void attend() {
        // given
        MemberSignupRequest member1 = MemberSignupRequest.builder()
                .memberEmail("Jake95@naver.com")
                .memberNickname("J크")
                .memberPassword("qwer1234")
                .memberBirthDate(LocalDateTime.now())
                .memberName("Jake")
                .build();

        memberService.signUp(member1);

        MemberLoginRequest request = MemberLoginRequest.builder()
                .memberEmail("Jake95@naver.com")
                .memberPassword("qwer1234")
                .build();

        ApiResponse<MemberLoginResponse> login = memberService.login(request);
        Long memberId = login.getData().getMemberId();

        MemberAttendRequest memberAttendRequest = MemberAttendRequest.builder()
                .memberId(memberId)
                .memberDate(LocalDateTime.now())
                .build();

        ApiResponse<String> attend = attendService.attend(memberAttendRequest);
        Member memberResult = memberRepository.findByMemberId(memberId);
        // when
        Attend byAttendId = attendRepository.findByAttendId(memberResult.getAttendId());
        // then
        assertThat(attend.getCode()).isEqualTo(200);
        assertThat(byAttendId.getAttendIsAttended()).isTrue();
        assertThat(memberResult.getMemberAttendance()).isEqualTo(1);
    }

    @DisplayName("출석 날짜가 똑같거나 이미 출석 인증을 했을 경우 예외를 처리한다.")
    @Test
    void attendAlreadyDid() {
        // given
        MemberSignupRequest member1 = MemberSignupRequest.builder()
                .memberEmail("Jake95@naver.com")
                .memberNickname("J크")
                .memberPassword("qwer1234")
                .memberBirthDate(LocalDateTime.now())
                .memberName("Jake")
                .build();

        memberService.signUp(member1);

        MemberLoginRequest request = MemberLoginRequest.builder()
                .memberEmail("Jake95@naver.com")
                .memberPassword("qwer1234")
                .build();

        ApiResponse<MemberLoginResponse> login = memberService.login(request);
        Long memberId = login.getData().getMemberId();

        MemberAttendRequest memberAttendRequest = MemberAttendRequest.builder()
                .memberId(memberId)
                .memberDate(LocalDateTime.now())
                .build();

        ApiResponse<String> attend = attendService.attend(memberAttendRequest);
        Member memberResult = memberRepository.findByMemberId(memberId);
        Attend byAttendId = attendRepository.findByAttendId(memberResult.getAttendId());

        // when
        ApiResponse<String> attend2 = attendService.attend(memberAttendRequest);

        // then
        assertThat(attend2.getCode()).isEqualTo(400);
        assertThat(attend2.getMessage()).isEqualTo("이미 출석 인증을 하셨습니다.");
    }

}