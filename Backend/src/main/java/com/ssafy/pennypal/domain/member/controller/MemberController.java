package com.ssafy.pennypal.domain.member.controller;


import com.ssafy.pennypal.domain.member.dto.request.MemberSignupRequest;
import com.ssafy.pennypal.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RequestMapping("/api/member")
@RestController
public class MemberController {

    private final MemberService memberService;

    MemberSignupRequest memberSignupRequest = MemberSignupRequest.builder()
            .memberEmail("Jake95@naver.com")
            .memberNickname("J크")
            .memberPassword("qwer1234")
            .memberBirthDate(LocalDateTime.now())
            .memberName("Jake")
            .build();

}
