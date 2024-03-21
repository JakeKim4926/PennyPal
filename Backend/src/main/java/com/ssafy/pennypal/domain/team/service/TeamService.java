package com.ssafy.pennypal.domain.team.service;

import com.ssafy.pennypal.domain.member.entity.Member;
import com.ssafy.pennypal.domain.member.repository.IMemberRepository;
import com.ssafy.pennypal.domain.team.dto.request.TeamCreateRequest;
import com.ssafy.pennypal.domain.team.dto.request.TeamCreateServiceRequest;
import com.ssafy.pennypal.domain.team.dto.response.TeamCreateResponse;
import com.ssafy.pennypal.domain.team.dto.response.TeamJoinResponse;
import com.ssafy.pennypal.domain.team.entity.Team;
import com.ssafy.pennypal.domain.team.repository.ITeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamService {

    private final ITeamRepository teamRepository;
    private final IMemberRepository memberRepository;

    public TeamCreateResponse createTeam(TeamCreateServiceRequest request) {
        // 유저 정보 가져오기
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

    public TeamJoinResponse joinTeam(Long teamId, Long memberId){

        // 팀 정보 가져오기
        Team team = teamRepository.findByTeamId(teamId);

        if(team != null){

            // 유저 정보 조회
            Member member = memberRepository.findByMemberId(memberId);

            // 팀 인원 6명이면 예외 발생
            if(team.getMembers().size() == 6){
                throw new IllegalArgumentException("팀 인원이 가득 찼습니다.");
            }

            // 팀 구성원에 포함 돼 있는지 확인
                if(team.getMembers().contains(member)){
                    throw new IllegalArgumentException("이미 가입한 팀입니다.");
                }

            // 이미 다른 팀의 구성원인지 확인
            if(member.getTeam() != null){
                throw new IllegalArgumentException("이미 가입된 팀이 있습니다.");
            }

            // 팀 자동승인 여부에 따라...
            if(team.getTeamIsAutoConfirm()){
                // 자동 승인이라면 바로 추가
                team.getMembers().add(member);
                member.setTeam(team);
            }else{
                // 수동 승인이라면 대기 리스트에 추가하고 예외 던져주기
                team.getWaitingList().add(member);
                throw new IllegalArgumentException("가입 요청이 완료되었습니다.");
            }

        } else {
            throw new IllegalArgumentException("팀 정보를 찾을 수 없습니다.");
            }
        return TeamJoinResponse.of(team);
    }

}
