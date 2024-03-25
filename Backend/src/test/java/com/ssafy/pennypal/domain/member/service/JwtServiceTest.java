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
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @DisplayName("멤버의 정보를 받아 이메일을 Jwt 토큰으로 발급한다.")
    @Test
    void test() {
        // given
        Member member = Member.builder()
                .memberEmail("Jake95@naver.com")
                .memberNickname("J크")
                .memberPassword(passwordEncoder.encode("qwer1234"))
                .memberBirthDate(LocalDateTime.now())
                .memberName("Jake")
                .build();


        // when
        var token = jwtService.generateToken(member);

        // then
        System.out.println(token);
        assertThat(token).isNotNull();
    }

}