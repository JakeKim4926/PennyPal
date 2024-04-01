package com.ssafy.pennypal.domain.member.service;

import com.ssafy.pennypal.domain.member.dto.request.MemberLoginRequest;
import com.ssafy.pennypal.domain.member.dto.request.MemberSignupRequest;
import com.ssafy.pennypal.domain.member.dto.request.MemberUpdateNicknameRequest;
import com.ssafy.pennypal.domain.member.dto.request.MemberUpdatePasswordRequest;
import com.ssafy.pennypal.domain.member.dto.response.MemberLoginResponse;
import com.ssafy.pennypal.domain.member.dto.response.MemberSignupResponse;
import com.ssafy.pennypal.domain.member.entity.Member;
import com.ssafy.pennypal.domain.member.repository.IMemberRepository;
import com.ssafy.pennypal.global.common.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final IMemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public MemberSignupResponse signUp(MemberSignupRequest memberSignupRequest) {
        Member memberByEmail = memberRepository.findByMemberEmail(memberSignupRequest.getMemberEmail());
        if(memberByEmail != null)
            return MemberSignupResponse.builder()
                    .code(400)
                    .status(HttpStatus.BAD_REQUEST)
                    .message("이미 사용 중인 이메일 입니다.")
                    .build();

        Member memberByNickname = memberRepository.findByMemberNickname(memberSignupRequest.getMemberNickname());
        if(memberByNickname != null)
            return MemberSignupResponse.builder()
                    .code(400)
                    .status(HttpStatus.BAD_REQUEST)
                    .message("이미 사용 중인 닉네임 입니다.")
                    .build();

        Member member = Member.builder()
                .memberEmail(memberSignupRequest.getMemberEmail())
                .memberNickname(memberSignupRequest.getMemberNickname())
                .memberPassword(passwordEncoder.encode(memberSignupRequest.getMemberPassword()))
                .memberBirthDate(memberSignupRequest.getMemberBirthDate())
                .memberName(memberSignupRequest.getMemberName())
                .memberAttendance(0)
                .build();

        memberRepository.save(member);

        return MemberSignupResponse.builder()
                .status(HttpStatus.OK)
                .message("회원 가입 성공")
                .build();

    }

    public ApiResponse<MemberLoginResponse> login(MemberLoginRequest memberLoginRequest) {
        Member memberByEmail = memberRepository.findByMemberEmail(memberLoginRequest.getMemberEmail());
        if(memberByEmail == null) {
            return ApiResponse.of(HttpStatus.BAD_REQUEST, "존재하지 않는 ID 입니다.", null);
        }

        if(!passwordEncoder.matches(memberLoginRequest.getMemberPassword(), memberByEmail.getMemberPassword())){
            return ApiResponse.of(HttpStatus.UNAUTHORIZED, "비밀 번호를 다시 입력해주세요.", null);
        }

        MemberLoginResponse response = MemberLoginResponse.builder()
                .memberId(memberByEmail.getMemberId())
                .memberNickname(memberByEmail.getMemberNickname())
                .memberToken(jwtService.generateToken(memberByEmail))
                .build();

        return ApiResponse.of(HttpStatus.OK, "로그인에 성공하셨습니다.", response);
    }

    public ApiResponse<String> updateNickname(MemberUpdateNicknameRequest memberUpdateNicknameRequest) {
        Member member = memberRepository.findByMemberId(memberUpdateNicknameRequest.getMemberId());
        if(member == null)
            return ApiResponse.of(HttpStatus.BAD_REQUEST, "존재하지 않는 계정 입니다.", memberUpdateNicknameRequest.getMemberNickname());
        
        Member memberByNickname = memberRepository.findByMemberNickname(memberUpdateNicknameRequest.getMemberNickname());
        if(memberByNickname != null)
            return ApiResponse.of(HttpStatus.BAD_REQUEST, "이미 사용 중인 닉네임 입니다.", memberUpdateNicknameRequest.getMemberNickname());

        member.setMemberNickname(memberUpdateNicknameRequest.getMemberNickname());

        memberRepository.save(member);

        return ApiResponse.of(HttpStatus.OK, "닉네임 변경에 성공하셨습니다.", memberUpdateNicknameRequest.getMemberNickname());
    }

    public ApiResponse<String> updatePassword(MemberUpdatePasswordRequest memberUpdatePasswordRequest) {

        Member member = memberRepository.findByMemberId(memberUpdatePasswordRequest.getMemberId());
        if(!passwordEncoder.matches(memberUpdatePasswordRequest.getMemberOriginPassword(), member.getPassword()))
            return ApiResponse.of(HttpStatus.UNAUTHORIZED, "비밀번호가 잘못되었습니다.", null);

        member.setMemberPassword(passwordEncoder.encode(memberUpdatePasswordRequest.getMemberChangePassword()));
        
        memberRepository.save(member);

        return ApiResponse.of(HttpStatus.OK, "비밀번호 변경에 성공하셨습니다.", null);
    }

}
