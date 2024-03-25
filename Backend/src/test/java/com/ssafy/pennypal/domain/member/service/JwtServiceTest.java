package com.ssafy.pennypal.domain.member.service;

import com.ssafy.pennypal.domain.member.dto.request.MemberSignupRequest;
import com.ssafy.pennypal.domain.member.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtServiceTest {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserDetailsService userDetailsService;

    @DisplayName("멤버의 정보를 받아 이메일을 Jwt 토큰으로 발급한다.")
    @Test
    void getJwtToken() {
        // given
        Member member = Member.builder()
                .memberEmail("Jake95@naver.com")
                .memberNickname("J크")
                .memberPassword(passwordEncoder.encode("qwer1234"))
                .memberBirthDate(LocalDateTime.now())
                .memberName("Jake")
                .build();


        // when
        String token = jwtService.generateToken(member);

        // then
        System.out.println(token);
        assertThat(token).isNotNull();
    }

    @DisplayName("발급 받은 토큰에서 이메일을 추출한다.")
    @Test
    void extractEmailFromJWT() {
        // given
        Member member = Member.builder()
                .memberEmail("Jake95@naver.com")
                .memberNickname("J크")
                .memberPassword(passwordEncoder.encode("qwer1234"))
                .memberBirthDate(LocalDateTime.now())
                .memberName("Jake")
                .build();

        String token = jwtService.generateToken(member);

        // when
        String memberEmail = jwtService.extractUsername(token);

        // then
        assertThat(member.getMemberEmail()).isEqualTo(memberEmail);
    }

    @DisplayName("추출한 이메일을 통해 Member의 정보를 받아와 토큰의 유효성을 검사하여 유효 시 true를 반환한다.")
    @Test
    void memberIsValid() {
        // given
        Member member = Member.builder()
                .memberEmail("Jake95@naver.com")
                .memberNickname("J크")
                .memberPassword(passwordEncoder.encode("qwer1234"))
                .memberBirthDate(LocalDateTime.now())
                .memberName("Jake")
                .build();

        String token = jwtService.generateToken(member);
        String memberEmail = jwtService.extractUsername(token);

        // when
        UserDetails userDetails = member;
//        UserDetails userDetails = userDetailsService.loadUserByUsername(memberEmail);

        // then
        assertThat(jwtService.isTokenValid(token, userDetails)).isTrue();
    }

    @DisplayName("추출한 이메일을 통해 Member의 정보를 받아와 토큰의 유효기간 만료 시 false를 반환한다.")
    @Test
    void tokenIsExpired() throws InterruptedException {
        // given
        Member member = Member.builder()
                .memberEmail("Jake95@naver.com")
                .memberNickname("J크")
                .memberPassword(passwordEncoder.encode("qwer1234"))
                .memberBirthDate(LocalDateTime.now())
                .memberName("Jake")
                .build();

        jwtService.EXPIRED_TIME = 1000;

        String token = jwtService.generateToken(member);
        String memberEmail = jwtService.extractUsername(token);


        Thread.sleep(1100);

        // when
        UserDetails userDetails = member;
//        UserDetails userDetails = userDetailsService.loadUserByUsername(memberEmail);

        // then
        assertThat(jwtService.isTokenValid(token, userDetails)).isFalse();
    }
}