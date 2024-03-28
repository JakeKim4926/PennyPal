package com.ssafy.pennypal.domain.member.controller;


import com.ssafy.pennypal.domain.member.dto.request.*;
import com.ssafy.pennypal.domain.member.dto.response.MemberLoginResponse;
import com.ssafy.pennypal.domain.member.dto.response.MemberSignupResponse;
import com.ssafy.pennypal.domain.member.service.AttendService;
import com.ssafy.pennypal.domain.member.service.MemberService;
import com.ssafy.pennypal.global.common.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("api/member")
@RestController
public class MemberController {

    private final MemberService memberService;
    private final AttendService attendService;

    @PostMapping("/signup")
    public MemberSignupResponse signup(@RequestBody MemberSignupRequest memberSignupRequest) {
        return memberService.signUp(memberSignupRequest);
    }

    @GetMapping("/login")
    public ApiResponse<MemberLoginResponse> login(@RequestBody MemberLoginRequest memberLoginRequest) {
        return memberService.login(memberLoginRequest);
    }

    @PatchMapping("/nickname")
    public ApiResponse<String> updateNickname(@RequestBody MemberUpdateNicknameRequest memberUpdateNicknameRequest) {
        return memberService.updateNickname(memberUpdateNicknameRequest);
    }

    @PatchMapping("/password")
    public ApiResponse<String> updatePassword(@RequestBody MemberUpdatePasswordRequest memberUpdatePasswordRequest) {
        return memberService.updatePassword(memberUpdatePasswordRequest);
    }

    @GetMapping("/attend")
    public ApiResponse<String> attend(@RequestBody MemberAttendRequest memberAttendRequest) {
        return attendService.attend(memberAttendRequest);
    }

    @GetMapping("/attend/state")
    public ApiResponse<Boolean> attend(@RequestParam Long memberId) {
        return attendService.getIsAttended(memberId);
    }
}
