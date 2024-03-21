package com.ssafy.pennypal.domain.team.service;

import com.ssafy.pennypal.domain.member.entity.Member;
import com.ssafy.pennypal.domain.member.repository.IMemberRepository;
import com.ssafy.pennypal.domain.team.dto.request.TeamCreateServiceRequest;
import com.ssafy.pennypal.domain.team.dto.request.TeamJoinServiceRequest;
import com.ssafy.pennypal.domain.team.dto.response.TeamCreateResponse;
import com.ssafy.pennypal.domain.team.dto.response.TeamJoinResponse;
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
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


@ActiveProfiles("test")
@SpringBootTest
@Transactional
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

    @DisplayName("팀을 만드는 유저가 이미 다른 팀의 구성원이라면 예외가 발생한다.")
    @Test
    void memberCanHaveOneTeam(){
        // given
        Member member1 = createMember("member1@pennypal.site", "짠1", LocalDateTime.now());
        Member member2 = createMember("member2@pennypal.site", "짠2", LocalDateTime.now());

        memberRepository.saveAll(List.of(member1, member2));

        TeamCreateServiceRequest request1 = createServiceRequest("팀이름3", true, member1.getMemberId(),"팀소개");
        TeamCreateServiceRequest request2 = createServiceRequest("팀이름4", false, member2.getMemberId(),"팀소개");

        teamService.createTeam(request1);
        teamService.createTeam(request2);


        TeamCreateServiceRequest request3 = createServiceRequest("팀이름5", true, member1.getMemberId(),"팀소개");

        // when, then
        assertThatThrownBy(() -> teamService.createTeam(request3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("한 개의 팀에만 가입 가능합니다.");

    }

    // todo : refactoring
    @DisplayName("팀의 인원이 6명이라면 가입할 수 없다.")
    @Test
    void teamMembersSizeMustBeLessThan6(){
        // given

        // 유저 7명 생성, 저장
        List<Member> members = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            members.add(createMember("member" + i + "@pennypal.site",
                    "짠" + i, LocalDateTime.now()));
        }
        memberRepository.saveAll(members.subList(0, 6));

        // 팀 생성, 저장
        Team team = createTeam("팀이름1", true, members.get(0).getMemberId(), "팀소개1", members.get(0));

        Team savedTeam = teamRepository.save(team);

        // 팀 6명 가입
        for (Member member : members.subList(0, 5)) {
            savedTeam.getMembers().add(member);
            member.setTeam(savedTeam);
        }

        TeamJoinServiceRequest joinRequest = joinServiceRequest(savedTeam.getTeamId(), members.get(6).getMemberId());

        // when, then
        assertThatThrownBy(() -> teamService.joinTeam(joinRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("팀 인원이 가득 찼습니다.");

    }

    @DisplayName("해당 팀에 이미 가입되어 있으면 가입할 수 없다.")
    @Test
    void cannotSignUpSameTeam(){
        // given
        Member member = createMember("member1@pennypal.site", "짠1", LocalDateTime.now());
        memberRepository.save(member);

        Team team = createTeam("팀이름1", true, 1L, "팀소개1", member);
        teamRepository.save(team);

        team.getMembers().add(member);


        TeamJoinServiceRequest joinRequest = joinServiceRequest(team.getTeamId(), member.getMemberId());

        // when, then
        assertThatThrownBy(() -> teamService.joinTeam(joinRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 가입한 팀입니다.");
    }

    @DisplayName("다른 팀에 가입 되어 있다면 가입할 수 없다.")
    @Test
    void cannotSignUpAnotherTeam(){
        // given
        Member member1 = createMember("member1@pennypal.site", "짠1", LocalDateTime.now());
        Member member2 = createMember("member2@pennypal.site", "짠2", LocalDateTime.now());
        Member member3 = createMember("member3@pennypal.site", "짠3", LocalDateTime.now());
        memberRepository.saveAll(List.of(member1, member2, member3));

        Team team1 = createTeam("팀이름1", true, member1.getMemberId(), "팀소개1", member1);
        Team team2 = createTeam("팀이름2", true, member2.getMemberId(), "팀소개2", member2);
        teamRepository.saveAll(List.of(team1, team2));

        // member3 -> team1 가입
        team1.getMembers().add(member3);
        member3.setTeam(team1);

        TeamJoinServiceRequest joinRequest = joinServiceRequest(team2.getTeamId(), member3.getMemberId());

        // when, then
        assertThatThrownBy(() -> teamService.joinTeam(joinRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 가입된 팀이 있습니다.");

    }

    // todo : assertThat 부분 확실하게 만들기
    @DisplayName("팀의 가입 승인 방식이 자동이라면 바로 가입되며, 해당 팀의 구성원은 1명 늘어난다.")
    @Test
    void TeamIsAutoConfirm(){
        // given
        Member member1 = createMember("member1@pennypal.site", "짠1", LocalDateTime.now());
        Member member2 = createMember("member2@pennypal.site", "짠2", LocalDateTime.now());
        memberRepository.saveAll(List.of(member1, member2));

        TeamCreateServiceRequest createRequest = createServiceRequest("팀이름1", true, member1.getMemberId(),"팀소개");
        TeamCreateResponse savedTeam = teamService.createTeam(createRequest);

        Team findedTeam = teamRepository.findByTeamName(savedTeam.getTeamName());

        TeamJoinServiceRequest joinRequest = joinServiceRequest(findedTeam.getTeamId(), member2.getMemberId());

        // when
        TeamJoinResponse response = teamService.joinTeam(joinRequest);
        Team updatedTeam = teamRepository.findByTeamName("팀이름1");

        //then
        assertThat(updatedTeam.getTeamName()).isEqualTo("팀이름1");
        assertThat(updatedTeam.getMembers()).hasSize(2);
//        assertThat(updatedTeam.getMembers())
//                .extracting(Member::getMemberNickname)
//                .containsExactlyInAnyOrder("짠1", "짠2");
    }

    // todo
    @DisplayName("팀의 가입 승인 방식이 자동이 아니라면 팀의 가입 대기 리스트에 유저 정보가 들어가고, 예외가 발생한다.")
    @Test
    void test(){
        // given

        // when

        // then

    }

    private Member createMember(String memberEmail, String memberNickname, LocalDateTime memberBirthDate){
        return Member.builder()
                .memberEmail(memberEmail)
                .memberPassword("1234")
                .memberName("김김짠돌")
                .memberNickname(memberNickname)
                .memberBirthDate(memberBirthDate)
                .build();
    }

    private Team createTeam(String teamName, Boolean teamIsAutoConfirm, Long teamLeaderId, String teamInfo, Member member){
        return Team.builder()
                .teamName(teamName)
                .teamIsAutoConfirm(teamIsAutoConfirm)
                .teamLeaderId(teamLeaderId)
                .teamInfo(teamInfo)
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

    private TeamJoinServiceRequest joinServiceRequest(Long teamId, Long memberId){
        return TeamJoinServiceRequest.builder()
                .teamId(teamId)
                .memberId(memberId)
                .build();
    }
}