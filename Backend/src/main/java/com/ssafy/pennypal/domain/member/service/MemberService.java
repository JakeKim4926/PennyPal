package com.ssafy.pennypal.domain.member.service;

import com.ssafy.pennypal.domain.member.dto.request.MemberSignupRequest;
import com.ssafy.pennypal.domain.member.entity.Member;
import com.ssafy.pennypal.domain.member.entity.MemberErrorStatus;
import com.ssafy.pennypal.domain.member.repository.IMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final IMemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    public int signUp(MemberSignupRequest memberSignupRequest) {
        Member member = Member.builder()
                .memberEmail(memberSignupRequest.getMemberEmail())
                .memberNickname(memberSignupRequest.getMemberNickname())
                .memberPassword(passwordEncoder.encode(memberSignupRequest.getMemberPassword()))
                .memberBirthDate(memberSignupRequest.getMemberBirthDate())
                .memberName(memberSignupRequest.getMemberName())
                .build();

        Member memberByEmail = memberRepository.findByMemberEmail(member.getMemberEmail());

        if(memberByEmail != null)
            return MemberErrorStatus.EMAIL_EXIST.getValue();

        Member memberByNickname = memberRepository.findByMemberNickname(member.getMemberNickname());

        if(memberByNickname != null)
            return MemberErrorStatus.NICKNAME_EXIST.getValue();

        memberRepository.save(member);

        return HttpStatus.OK.value();
    }

}
