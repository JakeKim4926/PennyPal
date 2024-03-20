package com.ssafy.pennypal.domain.team.service;

import com.ssafy.pennypal.domain.member.entity.Member;
import com.ssafy.pennypal.domain.member.repository.IMemberRepository;
import com.ssafy.pennypal.domain.team.dto.request.TeamCreateRequest;
import com.ssafy.pennypal.domain.team.dto.request.TeamCreateServiceRequest;
import com.ssafy.pennypal.domain.team.dto.response.TeamCreateResponse;
import com.ssafy.pennypal.domain.team.entity.Team;
import com.ssafy.pennypal.domain.team.repository.ITeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamService {

    private final ITeamRepository teamRepository;
    private final IMemberRepository memberRepository;

    public TeamCreateResponse createTeam(TeamCreateServiceRequest request) {
        // 유저 찾기
        Member member = memberRepository.findByMemberId(request.getTeamLeaderId());

        // 이미 존재하는 팀명이라면 예외 발생
        if(teamRepository.findByTeamName(request.getTeamName()) != null){
            throw new IllegalArgumentException("이미 사용 중인 팀명입니다.");
        }

        // 유저가 포함된 팀 있는지 확인
        if(member.getTeam() != null){
            throw new IllegalArgumentException("한 개의 팀에만 가입 가능합니다.");
        }

        // 팀 생성
        Team team = Team.builder()
                .teamName(request.getTeamName())
                .teamIsAutoConfirm(request.getTeamIsAutoConfirm())
                .teamLeaderId(request.getTeamLeaderId())
                .build();

        // 생성된 팀 구성원에 유저 추가
        team.getMembers().add(member);

        // 팀 저장
        Team savedTeam = teamRepository.save(team);

        // 유저 team 정보 수정
        member.setTeam(team);
        memberRepository.save(member);

        return TeamCreateResponse.of(savedTeam);
    }


}
