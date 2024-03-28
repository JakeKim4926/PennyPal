package com.ssafy.pennypal.domain.member.service;

import com.ssafy.pennypal.domain.member.dto.request.MemberAttendRequest;
import com.ssafy.pennypal.domain.member.dto.request.MemberLoginRequest;
import com.ssafy.pennypal.domain.member.entity.Attend;
import com.ssafy.pennypal.domain.member.entity.Member;
import com.ssafy.pennypal.domain.member.repository.IAttendRepository;
import com.ssafy.pennypal.domain.member.repository.IMemberRepository;
import com.ssafy.pennypal.global.common.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AttendService {

    private final MemberService memberService;
    private final IMemberRepository memberRepository;
    private final IAttendRepository attendRepository;
    public ApiResponse<String> attend(MemberAttendRequest memberAttendRequest) {
        Member member = memberRepository.findByMemberId(memberAttendRequest.getMemberId());
        if(member == null)
            return ApiResponse.of(HttpStatus.BAD_REQUEST, "존재하지 않는 계정입니다.");

        Attend attendById = attendRepository.findByAttendId(member.getAttendId());
        if(attendById == null) {
            Attend attend = Attend.builder()
                    .attendLastDate(memberAttendRequest.getMemberDate())
                    .attendIsAttended(true)
                    .build();

            Attend save = attendRepository.save(attend);

            member.setAttendId(save.getAttendId());
            member.setMemberAttendance(1);
            memberRepository.save(member);

            return ApiResponse.ok("출석 인증에 성공하셨습니다.");
        }

        // 출석 날짜가 똑같거나 이미 출석 인증을 하였다..
        if(attendById.getAttendLastDate().toLocalDate().isEqual(memberAttendRequest.getMemberDate().toLocalDate())
        || attendById.getAttendIsAttended())
            return ApiResponse.of(HttpStatus.BAD_REQUEST, "이미 출석 인증을 하셨습니다.", "");

        attendById.setAttendIsAttended(true);
        attendById.setAttendLastDate(memberAttendRequest.getMemberDate());
        attendRepository.save(attendById);

        member.setMemberAttendance(member.getMemberAttendance() + 1);
        memberRepository.save(member);

        return ApiResponse.ok("출석 인증에 성공하셨습니다.");
    }

    public ApiResponse<Boolean> getIsAttended(Long memberId) {
        Member member = memberRepository.findByMemberId(memberId);
        if(member == null)
            return ApiResponse.of(HttpStatus.OK, "존재하지 않는 계정입니다.", false);

        if(member.getAttendId() == null)
            return ApiResponse.of(HttpStatus.OK,false);

        Attend byAttendId = attendRepository.findByAttendId(member.getAttendId());
        
        if(byAttendId.getAttendLastDate() == null || !byAttendId.getAttendLastDate().toLocalDate().isEqual(LocalDateTime.now().toLocalDate()))
            return ApiResponse.of(HttpStatus.OK, false);

        return ApiResponse.of(HttpStatus.OK, true);
    }
}
