package com.ssafy.pennypal.domain.team.entity;

import com.ssafy.pennypal.domain.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TeamTest {

    @DisplayName("팀 생성 시 팀 점수는 0이다.")
    @Test
    void teamScore(){
        // given
        Member member1 = createMember("member1@pennypal.site", "짠1", LocalDate.now());
        Member member2 = createMember("member2@pennypal.site", "짠2", LocalDate.now());

        // when
        Team team1 = createTeam("팀이름1", true, member1.getMemberId(), "팀소개1");

        // then
        assertThat(team1.getTeamScore()).isEqualTo(0);

    }

    @DisplayName("팀 생성 시 팀의 현재 인원은 1명이다.")
    @Test
    void teamNumberIsEqual1(){
        // given
        Member member1 = createMember("member1@pennypal.site", "짠1", LocalDate.now());
        Member member2 = createMember("member2@pennypal.site", "짠2", LocalDate.now());

        // when
        Team team1 = createTeam("팀이름1", true, member1.getMemberId(), "팀소개1");

        // then
        assertThat(team1.getMembers().size()).isEqualTo(1);

    }



    private Member createMember(String memberEmail, String memberNickname, LocalDate memberBirthDate){
        return Member.builder()
                .memberEmail(memberEmail)
                .memberPassword("1234")
                .memberName("김김짠돌")
                .memberNickname(memberNickname)
                .memberBirthDate(memberBirthDate)
                .build();
    }

    private Team createTeam(String teamName, Boolean teamIsAutoConfirm, Long teamLeaderId, String teamInfo){
        return Team.builder()
                .teamName(teamName)
                .teamIsAutoConfirm(teamIsAutoConfirm)
                .teamLeaderId(teamLeaderId)
                .teamInfo(teamInfo)
                .build();
    }



}