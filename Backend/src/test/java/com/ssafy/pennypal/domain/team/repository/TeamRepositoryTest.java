package com.ssafy.pennypal.domain.team.repository;

import com.ssafy.pennypal.domain.member.entity.Member;
import com.ssafy.pennypal.domain.member.repository.IMemberRepository;
import com.ssafy.pennypal.domain.team.entity.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
@Transactional(readOnly = true)
class TeamRepositoryTest {

    @Autowired
    private ITeamRepository teamRepository;

    @Autowired
    private IMemberRepository memberRepository;

    @DisplayName("팀 아이디로 팀의 정보를 가져온다.")
    @Test
    @Transactional
    void findByTeamId(){
        // given
        Member member = createMember("member1@pennypal.site", "짠1", LocalDateTime.now());

        Team team = createTeam("팀이름1", true, 1L, "팀소개1", member);
        Team savedTeam = teamRepository.save(team);

        // when
        Team findedTeam = teamRepository.findByTeamId(savedTeam.getTeamId())
                        .orElseThrow(() -> new IllegalArgumentException("팀 정보를 찾을 수 없습니다."));

        // then
        assertThat(findedTeam)
                .extracting("teamName", "teamLeaderId", "teamInfo")
                .containsExactlyInAnyOrder("팀이름1", 1L, "팀소개1");

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


}