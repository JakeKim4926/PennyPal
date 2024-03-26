package com.ssafy.pennypal.domain.member.service;

import com.ssafy.pennypal.domain.member.dto.request.MemberSignupRequest;
import com.ssafy.pennypal.domain.member.dto.response.MemberSignupResponse;
import com.ssafy.pennypal.domain.member.entity.Member;
import com.ssafy.pennypal.domain.member.repository.IMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class MemberServiceTest {

    @Autowired
    IMemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @DisplayName("이메일,PW,닉네임,생년월일,이름을 입력 받고, PW는 암호화하여 Member의 정보를 DB에 저장한다.")
    @Test
    void signUp() {
        // given
        MemberSignupRequest memberSignupRequest = MemberSignupRequest.builder()
                .memberEmail("Jake95@naver.com")
                .memberNickname("J크")
                .memberPassword("qwer1234")
                .memberBirthDate(LocalDateTime.now())
                .memberName("Jake")
                .build();

        // when
        memberService.signUp(memberSignupRequest);
        
        // then
        assertThat(memberRepository.findByMemberEmail("Jake95@naver.com").getMemberNickname()).isEqualTo(Member.builder()
                .memberEmail(memberSignupRequest.getMemberEmail())
                .memberNickname(memberSignupRequest.getMemberNickname())
                .memberPassword(passwordEncoder.encode(memberSignupRequest.getMemberPassword()))
                .memberBirthDate(memberSignupRequest.getMemberBirthDate())
                .memberName(memberSignupRequest.getMemberName())
                .build().getMemberNickname());
    }

    @DisplayName("회원 정보 중 이메일 중복을 검사하여 중복 될 경우 예외 처리")
    @Test
    void isEmailExist() {
        // given
        MemberSignupRequest member1 = MemberSignupRequest.builder()
                .memberEmail("Jake95@naver.com")
                .memberNickname("J크")
                .memberPassword("qwer1234")
                .memberBirthDate(LocalDateTime.now())
                .memberName("Jake")
                .build();

        MemberSignupRequest member2 = MemberSignupRequest.builder()
                .memberEmail("Jake95@naver.com")
                .memberNickname("섭섭")
                .memberPassword("qwer1234")
                .memberBirthDate(LocalDateTime.now())
                .memberName("김준섭")
                .build();

        memberService.signUp(member1);
        // when
        MemberSignupResponse memberSignupResponse = memberService.signUp(member2);

        // then
//        assertThat(memberSignupResponse).isEqualTo(MemberSignupResponse.builder()
//                .status(HttpStatus.BAD_REQUEST)
//                .message("이미 사용 중인 이메일 입니다.")
//                .build());
        assertThat(memberSignupResponse.getMessage()).isEqualTo("이미 사용 중인 이메일 입니다.");

    }

    @DisplayName("회원 정보 중 닉네임 중복을 검사하여 중복 될 경우 예외 처리")
    @Test
    void isNicknameExist() {
        // given
        MemberSignupRequest member1 = MemberSignupRequest.builder()
                .memberEmail("Taek95@naver.com")
                .memberNickname("SCM")
                .memberPassword("qwer1234")
                .memberBirthDate(LocalDateTime.now())
                .memberName("오유택")
                .build();

        MemberSignupRequest member2 = MemberSignupRequest.builder()
                .memberEmail("Jake95@naver.com")
                .memberNickname("SCM")
                .memberPassword("qwer1234")
                .memberBirthDate(LocalDateTime.now())
                .memberName("김준섭")
                .build();

        memberService.signUp(member1);
        // when
        MemberSignupResponse memberSignupResponse = memberService.signUp(member2);

        // then
//        assertThat(memberSignupResponse).isEqualTo(MemberSignupResponse.builder()
//                .status(HttpStatus.BAD_REQUEST)
//                .message("이미 사용 중인 닉네임 입니다.")
//                .build());
        assertThat(memberSignupResponse.getMessage()).isEqualTo("이미 사용 중인 닉네임 입니다.");
    }

    @DisplayName("Id와 패스워드를 받아 로그인을 성공 후 토큰을 발급한다.")
    @Test
    public void test() throws Exception {
        // given

        // when

        // then

    }
}