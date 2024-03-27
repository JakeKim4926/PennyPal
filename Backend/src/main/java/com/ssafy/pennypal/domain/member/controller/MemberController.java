package com.ssafy.pennypal.domain.member.controller;


import com.ssafy.pennypal.domain.member.dto.request.MemberLoginRequest;
import com.ssafy.pennypal.domain.member.dto.request.MemberSignupRequest;
import com.ssafy.pennypal.domain.member.dto.response.MemberLoginResponse;
import com.ssafy.pennypal.domain.member.dto.response.MemberSignupResponse;
import com.ssafy.pennypal.domain.member.service.MemberService;
import com.ssafy.pennypal.global.common.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/member")
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public MemberSignupResponse signup(@RequestBody MemberSignupRequest memberSignupRequest) {
        return memberService.signUp(memberSignupRequest);
    }

    @GetMapping("/login")
    public ApiResponse<MemberLoginResponse> login(@RequestBody MemberLoginRequest memberLoginRequest) {
        return memberService.login(memberLoginRequest);
    }


}
