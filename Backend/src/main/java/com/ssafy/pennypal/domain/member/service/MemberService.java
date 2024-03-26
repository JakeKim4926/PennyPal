package com.ssafy.pennypal.domain.member.service;

import com.ssafy.pennypal.domain.member.dto.request.MemberSignupRequest;
import com.ssafy.pennypal.domain.member.dto.response.MemberSignupResponse;
import com.ssafy.pennypal.domain.member.entity.Member;
import com.ssafy.pennypal.domain.member.repository.IMemberRepository;
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
    public MemberSignupResponse signUp(MemberSignupRequest memberSignupRequest) {
        Member member = Member.builder()
                .memberEmail(memberSignupRequest.getMemberEmail())
                .memberNickname(memberSignupRequest.getMemberNickname())
                .memberPassword(passwordEncoder.encode(memberSignupRequest.getMemberPassword()))
                .memberBirthDate(memberSignupRequest.getMemberBirthDate())
                .memberName(memberSignupRequest.getMemberName())
                .build();

        Member memberByEmail = memberRepository.findByMemberEmail(member.getMemberEmail());

        if(memberByEmail != null)
            return MemberSignupResponse.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message("이미 사용 중인 이메일 입니다.")
                    .build();

        Member memberByNickname = memberRepository.findByMemberNickname(member.getMemberNickname());

        if(memberByNickname != null)
            return MemberSignupResponse.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message("이미 사용 중인 닉네임 입니다.")
                    .build();


        memberRepository.save(member);

        return MemberSignupResponse.builder()
                .status(HttpStatus.OK)
                .message("회원 가입 성공")
                .build();

    }

}
