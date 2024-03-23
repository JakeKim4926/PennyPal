package com.ssafy.pennypal.domain.team.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.pennypal.bank.service.api.BankServiceAPIImpl;
import com.ssafy.pennypal.bank.service.api.IBankServiceAPI;
import com.ssafy.pennypal.common.RestDocsSupport;
import com.ssafy.pennypal.domain.member.entity.Member;
import com.ssafy.pennypal.domain.member.repository.IMemberRepository;
import com.ssafy.pennypal.domain.team.dto.request.TeamCreateRequest;
import com.ssafy.pennypal.domain.team.dto.request.TeamCreateServiceRequest;
import com.ssafy.pennypal.domain.team.dto.request.TeamJoinRequest;
import com.ssafy.pennypal.domain.team.dto.request.TeamJoinServiceRequest;
import com.ssafy.pennypal.domain.team.dto.response.*;
import com.ssafy.pennypal.domain.team.entity.Team;
import com.ssafy.pennypal.domain.team.entity.TeamRankHistory;
import com.ssafy.pennypal.domain.team.service.TeamService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TeamControllerTest extends RestDocsSupport {

    private final BankServiceAPIImpl bankServiceAPI = mock(BankServiceAPIImpl.class);
    private final TeamService teamService = mock(TeamService.class);

    @Override
    protected Object initController() {
        return new TeamController(teamService, bankServiceAPI);
    }


    @DisplayName("팀 생성")
    @Test
    void createTeam() throws Exception {

        Member mockMember = mock(Member.class);

        // ID 1L의 Mock Member 생성
        given(mockMember.getMemberId()).willReturn(1L);

        TeamCreateRequest request = TeamCreateRequest.builder()
                .teamName("팀이름")
                .teamIsAutoConfirm(false)
                .teamLeaderId(mockMember.getMemberId())
                .teamInfo("팀소개")
                .build();

        // Mock MemberDetailResponse 생성
        TeamMemberDetailResponse mockMemberDetail = TeamMemberDetailResponse.builder()
                .memberNickname("팀원닉네임")
                .build();

        // stubbing
        given(teamService.createTeam(any(TeamCreateServiceRequest.class)))
                .willReturn(TeamCreateResponse.builder()
                        .teamName("팀이름")
                        .teamInfo("팀소개")
                        .teamScore(100)
                        .teamLeaderId(1L)
                        .members(Arrays.asList(mockMemberDetail))
                        .build());

        mockMvc.perform(
                        post("/api/team/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("create-team",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestFields(
                                        fieldWithPath("teamName").type(JsonFieldType.STRING)
                                                .description("팀 이름"),
                                        fieldWithPath("teamIsAutoConfirm").type(JsonFieldType.BOOLEAN)
                                                .description("자동가입 승인 여부"),
                                        fieldWithPath("teamLeaderId").type(JsonFieldType.NUMBER)
                                                .description("팀장 ID"),
                                        fieldWithPath("teamInfo").type(JsonFieldType.STRING)
                                                .description("팀 한줄소개")
                                ),
                                responseFields(
                                        fieldWithPath("code").type(JsonFieldType.NUMBER)
                                                .description("코드"),
                                        fieldWithPath("status").type(JsonFieldType.STRING)
                                                .description("상태"),
                                        fieldWithPath("message").type(JsonFieldType.STRING)
                                                .description("메시지"),
                                        fieldWithPath("data").type(JsonFieldType.OBJECT)
                                                .description("응답 데이터"),
                                        fieldWithPath("data.teamName").type(JsonFieldType.STRING)
                                                .description("팀 명"),
                                        fieldWithPath("data.teamInfo").type(JsonFieldType.STRING)
                                                .description("팀 한줄소개"),
                                        fieldWithPath("data.teamScore").type(JsonFieldType.NUMBER)
                                                .description("팀 포인트"),
                                        fieldWithPath("data.teamLeaderId").type(JsonFieldType.NUMBER)
                                                .description("팀장 ID"),

                                        subsectionWithPath("data.members").type(JsonFieldType.ARRAY)
                                                .description("팀 멤버 목록"),
                                        fieldWithPath("data.members[].memberNickname").type(JsonFieldType.STRING)
                                                .description("멤버 닉네임")
                                )
                        )
                );

    }

    @DisplayName("팀 가입")
    @Test
    void joinTeam() throws Exception {
        Member member = mock(Member.class);
        Team team = mock(Team.class);

        // memberId가 2L인 Mock Member 생성
        given(member.getMemberId()).willReturn(2L);

        // leaderId가 1L인 Mock Team 생성
        given(team.getTeamLeaderId()).willReturn(1L);

        TeamJoinRequest request = TeamJoinRequest.builder()
                .teamId(1L)
                .memberId(member.getMemberId())
                .build();

        // Mock MemberDetailResponse 생성
        TeamMemberDetailResponse mockMemberDetail = TeamMemberDetailResponse.builder()
                .memberNickname("팀원닉네임")
                .build();

        // teamService.joinTeam() 메서드의 Stubbing
        given(teamService.joinTeam(any(TeamJoinServiceRequest.class)))
                .willReturn(TeamJoinResponse.builder()
                        .teamName("팀 이름")
                        .teamInfo("팀 한줄소개")
                        .teamScore(100)
                        .teamLeaderId(1L)
                        .members(Arrays.asList(mockMemberDetail, mockMemberDetail))
                        .build());

        mockMvc.perform(
                        post("/api/team/{teamId}", 1L)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("join-team",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("teamId").type(JsonFieldType.NUMBER)
                                        .description("팀 ID"),
                                fieldWithPath("memberId").type(JsonFieldType.NUMBER)
                                        .description("유저 ID")
                        ), responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),

                                fieldWithPath("data").type(JsonFieldType.OBJECT)
                                        .description("응답 데이터"),
                                fieldWithPath("data.teamName").type(JsonFieldType.STRING)
                                        .description("팀 이름"),
                                fieldWithPath("data.teamInfo").type(JsonFieldType.STRING)
                                        .description("팀 한줄 소개"),
                                fieldWithPath("data.teamScore").type(JsonFieldType.NUMBER)
                                        .description("팀 포인트"),
                                fieldWithPath("data.teamLeaderId").type(JsonFieldType.NUMBER)
                                        .description("팀장 ID"),

                                subsectionWithPath("data.members").type(JsonFieldType.ARRAY)
                                        .description("팀 멤버 목록"),
                                fieldWithPath("data.members[].memberNickname").type(JsonFieldType.STRING)
                                        .description("멤버 닉네임")
                        )

                ));

        // 테스트에서 반환된 결과 확인
        verify(teamService).joinTeam(any(TeamJoinServiceRequest.class));
    }

    @DisplayName("팀 주간 랭킹 조회")
    @Test
    void weeklyTeamRanking() throws Exception{
        // given
        TeamRankHistoryResponse rankResponse1 = mock(TeamRankHistoryResponse.class);
        TeamRankHistoryResponse rankResponse2 = mock(TeamRankHistoryResponse.class);
        TeamRankHistoryResponse rankResponse3 = mock(TeamRankHistoryResponse.class);

        Team team1 = mock(Team.class);
        Team team2 = mock(Team.class);
        Team team3 = mock(Team.class);

        LocalDate rankDate = LocalDate.of(2024, 03, 25);

        List<Team> teams = Arrays.asList(team1, team2, team3);
        List<TeamRankHistoryResponse> rankResponses = Arrays.asList(rankResponse1, rankResponse2, rankResponse3);

        // Stubbing
        given(teamService.rankHistoriesForWeeks()).willReturn(rankResponses);
        given(rankResponse1.getTeamName()).willReturn("팀이름1");
        given(rankResponse2.getTeamName()).willReturn("팀이름2");
        given(rankResponse3.getTeamName()).willReturn("팀이름3");
        given(rankResponse1.getRankDate()).willReturn(rankDate);
        given(rankResponse2.getRankDate()).willReturn(rankDate);
        given(rankResponse3.getRankDate()).willReturn(rankDate);
        given(rankResponse1.getRankNum()).willReturn(3);
        given(rankResponse2.getRankNum()).willReturn(1);
        given(rankResponse3.getRankNum()).willReturn(2);




        mockMvc.perform(
                get("/api/team/rank/weekly")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("weekly-team-ranking",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),

                                fieldWithPath("data").type(JsonFieldType.ARRAY)
                                        .description("응답 데이터"),
                                fieldWithPath("data.[].teamName").type(JsonFieldType.STRING)
                                        .description("팀 이름"),
                                fieldWithPath("data.[].rankDate").type(JsonFieldType.ARRAY)
                                        .description("랭킹 기록 날짜"),
                                fieldWithPath("data.[].rankNum").type(JsonFieldType.NUMBER)
                                        .description("팀 등수")
                        )
                ));

        // Service 메서드가 호출되었는지 확인
        verify(teamService).rankHistoriesForWeeks();

    }

    @DisplayName("팀 실시간 랭킹 조회")
    @Test
    void realtimeTeamRanking() throws Exception {
        // given
        TeamRankResponse rankresponse1 = mock(TeamRankResponse.class);
        TeamRankResponse rankresponse2 = mock(TeamRankResponse.class);
        TeamRankResponse rankresponse3 = mock(TeamRankResponse.class);

        Team team1 = mock(Team.class);
        Team team2 = mock(Team.class);
        Team team3 = mock(Team.class);

        LocalDate rankDate = LocalDate.of(2024, 03, 25);

        List<Team> teams = Arrays.asList(team1, team2, team3);
        List<TeamRankResponse> mockRankResponses = Arrays.asList(rankresponse1, rankresponse2, rankresponse3);

        given(teamService.RankTeamRealTimeScore()).willReturn(mockRankResponses);
        given(rankresponse1.getTeamId()).willReturn(1L);
        given(rankresponse2.getTeamId()).willReturn(2L);
        given(rankresponse3.getTeamId()).willReturn(3L);
        given(rankresponse1.getTeamName()).willReturn("팀이름1");
        given(rankresponse2.getTeamName()).willReturn("팀이름2");
        given(rankresponse3.getTeamName()).willReturn("팀이름3");
        given(rankresponse1.getTeamScore()).willReturn(100);
        given(rankresponse2.getTeamScore()).willReturn(300);
        given(rankresponse3.getTeamScore()).willReturn(200);
        given(rankresponse1.getTeamRankNum()).willReturn(3);
        given(rankresponse2.getTeamRankNum()).willReturn(1);
        given(rankresponse3.getTeamRankNum()).willReturn(2);



        mockMvc.perform(
                        get("/api/team/rank/realtime")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("realtime-team-ranking",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),

                                fieldWithPath("data").type(JsonFieldType.ARRAY)
                                        .description("응답 데이터"),
                                fieldWithPath("data.[].teamId").type(JsonFieldType.NUMBER)
                                        .description("팀 ID"),
                                fieldWithPath("data.[].teamName").type(JsonFieldType.STRING)
                                        .description("팀 이름"),
                                fieldWithPath("data.[].teamScore").type(JsonFieldType.NUMBER)
                                        .description("팀 점수"),
                                fieldWithPath("data.[].teamRankNum").type(JsonFieldType.NUMBER)
                                        .description("팀 등수")
                        )
                ));

        // Service 메서드가 호출되었는지 확인
        verify(teamService).RankTeamRealTimeScore();


    }

    private Member createMember(String memberEmail, String memberNickname, LocalDateTime memberBirthDate) {
        return Member.builder()
                .memberEmail(memberEmail)
                .memberPassword("1234")
                .memberName("김김짠돌")
                .memberNickname(memberNickname)
                .memberBirthDate(memberBirthDate)
                .build();


    }

}