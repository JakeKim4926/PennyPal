package com.ssafy.pennypal.domain.team.service;

import com.ssafy.pennypal.domain.member.entity.Member;
import com.ssafy.pennypal.domain.member.repository.IMemberRepository;
import com.ssafy.pennypal.domain.team.dto.request.TeamCreateServiceRequest;
import com.ssafy.pennypal.domain.team.dto.response.TeamCreateResponse;
import com.ssafy.pennypal.domain.team.entity.Team;
import com.ssafy.pennypal.domain.team.repository.ITeamRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@ActiveProfiles("test")
@SpringBootTest
class TeamServiceTest {

    @Autowired
    private TeamService teamService;

    @Autowired
    private ITeamRepository teamRepository;

    @Autowired
    private IMemberRepository memberRepository;

    @AfterEach
    void tearDown() {
        memberRepository.deleteAllInBatch();
        teamRepository.deleteAllInBatch();
    }

    @DisplayName("팀을 만드는 유저의 아이디를 사용하여 팀을 생성한다.")
    @Test
    void createTeam(){
        // given
        Member member1 = createMember("member1@pennypal.site", "짠1", LocalDateTime.now());

        memberRepository.save(member1);

        TeamCreateServiceRequest request = createServiceRequest("팀이름1", true, member1.getMemberId(),"팀소개");

        // when
        TeamCreateResponse teamResponse = teamService.createTeam(request);

        // then
        assertThat(teamResponse)
                .extracting("teamLeaderId", "teamName")
                .contains(member1.getMemberId(), "팀이름1");

    }

    @DisplayName("이미 존재하는 팀명이라면 예외가 발생한다.")
    @Test
    void teamNameCannotSame(){
        // given
        Member member1 = createMember("member1@pennypal.site", "짠1", LocalDateTime.now());
        Member member2 = createMember("member2@pennypal.site", "짠2", LocalDateTime.now());

        memberRepository.saveAll(List.of(member1, member2));

        TeamCreateServiceRequest request1 = createServiceRequest("팀이름2", true, member1.getMemberId(),"팀소개");
        TeamCreateServiceRequest request2 = createServiceRequest("팀이름2", false, member2.getMemberId(),"팀소개");

        teamService.createTeam(request1);

        // when, then
        assertThatThrownBy(() -> teamService.createTeam(request2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 사용 중인 팀명입니다.");

    }

    // todo
//    @DisplayName("팀을 만드는 유저가 이미 다른 팀의 구성원이라면 예외가 발생한다.")
//    @Test
//    void memberCanHaveOneTeam(){
//        // given
//        Member member1 = createMember("member1@pennypal.site", "짠1", LocalDateTime.now());
//        Member member2 = createMember("member2@pennypal.site", "짠2", LocalDateTime.now());
//
//        memberRepository.saveAll(List.of(member1, member2));
//
//        TeamCreateServiceRequest request1 = createServiceRequest("팀이름3", true, member1.getMemberId(),"팀소개");
//        TeamCreateServiceRequest request2 = createServiceRequest("팀이름4", false, member2.getMemberId(),"팀소개");
//
//        teamService.createTeam(request1);
//        teamService.createTeam(request2);
//
//        Team team = Team.builder()
//                .teamName(request2.getTeamName())
//                .teamIsAutoConfirm(request2.getTeamIsAutoConfirm())
//                .teamLeaderId(request2.getTeamLeaderId())
//                .teamInfo(request2.getTeamInfo())
//                .build();
//
////        TeamCreateServiceRequest request3 = createServiceRequest("팀이름3", true, member1.getMemberId(),"팀소개");
//
//        // when, then
//        assertThatThrownBy(() -> member1.setTeam(team))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessage("한 개의 팀에만 가입 가능합니다.");
//
//    }


    private Member createMember(String memberEmail, String memberNickname, LocalDateTime memberBirthDate){
        return Member.builder()
                .memberEmail(memberEmail)
                .memberPassword("1234")
                .memberName("김김짠돌")
                .memberNickname(memberNickname)
                .memberBirthDate(memberBirthDate)
                .build();
    }

    private TeamCreateServiceRequest createServiceRequest(String teamName, Boolean teamIsAutoConfirm, Long teamLeaderId, String teamInfo){
        return TeamCreateServiceRequest.builder()
                .teamName(teamName)
                .teamIsAutoConfirm(teamIsAutoConfirm)
                .teamLeaderId(teamLeaderId)
                .teamInfo(teamInfo)
                .build();
    }

}