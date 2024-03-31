package com.ssafy.pennypal.domain.team.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.pennypal.bank.service.api.BankServiceAPIImpl;
import com.ssafy.pennypal.common.RestDocsSupport;
import com.ssafy.pennypal.domain.chat.entity.ChatRoom;
import com.ssafy.pennypal.domain.chat.service.ChatService;
import com.ssafy.pennypal.domain.member.dto.response.TeamLastEachTotalResponse;
import com.ssafy.pennypal.domain.member.dto.response.TeamThisEachTotalResponse;
import com.ssafy.pennypal.domain.member.entity.Member;
import com.ssafy.pennypal.domain.team.dto.SimpleTeamDto;
import com.ssafy.pennypal.domain.team.dto.request.*;
import com.ssafy.pennypal.domain.team.dto.response.*;
import com.ssafy.pennypal.domain.team.entity.Team;
import com.ssafy.pennypal.domain.team.service.TeamService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TeamControllerTest extends RestDocsSupport {

    private final BankServiceAPIImpl bankServiceAPI = mock(BankServiceAPIImpl.class);
    private final TeamService teamService = mock(TeamService.class);
    private final ChatService chatService = mock(ChatService.class);

    @Override
    protected Object initController() {
        return new TeamController(teamService, chatService, bankServiceAPI);
    }


    @DisplayName("팀 생성")
    @Test
    void createTeam() throws Exception {

        TeamMemberExpenseResponse member = mock(TeamMemberExpenseResponse.class);
        Team team = mock(Team.class);
        TeamLastEachTotalResponse teamLastEachTotalResponse = mock(TeamLastEachTotalResponse.class);
        TeamThisEachTotalResponse teamThisEachTotalResponse = mock(TeamThisEachTotalResponse.class);

        TeamCreateRequest request = TeamCreateRequest.builder()
                .teamName("팀이름")
                .teamIsAutoConfirm(false)
                .teamLeaderId(1L)
                .teamInfo("팀소개")
                .build();

        given(team.getTeamId()).willReturn(1L);
        given(member.getMemberNickname()).willReturn("멤버 닉네임");
        given(teamLastEachTotalResponse.getDate()).willReturn(LocalDate.of(2024, 3, 28));
        given(teamLastEachTotalResponse.getTotalAmount()).willReturn(300000);
        given(teamThisEachTotalResponse.getDate()).willReturn(LocalDate.of(2024, 3, 28));
        given(teamThisEachTotalResponse.getTotalAmount()).willReturn(300000);

        // stubbing
        given(teamService.createTeam(any(TeamCreateServiceRequest.class)))
                .willReturn(TeamDetailResponse.builder()
                        .teamId(1L)
                        .teamName("팀이름")
                        .teamLeaderId(1L)
                        .teamInfo("팀 한줄소개")
                        .teamScore(0)
                        .teamRankRealtime(1)
                        .teamLastTotalExpenses(30000)
                        .teamThisTotalExpenses(200000)
                        .teamLastEachTotalExpenses(List.of(teamLastEachTotalResponse))
                        .teamThisEachTotalExpenses(List.of(teamThisEachTotalResponse))
                        .members(Arrays.asList(member))
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
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),

                                fieldWithPath("data").type(JsonFieldType.OBJECT)
                                        .description("응답 데이터"),
                                fieldWithPath("data.teamId").type(JsonFieldType.NUMBER)
                                        .description("팀 ID"),
                                fieldWithPath("data.teamName").type(JsonFieldType.STRING)
                                        .description("팀 명"),
                                fieldWithPath("data.teamLeaderId").type(JsonFieldType.NUMBER)
                                        .description("팀장 ID"),
                                fieldWithPath("data.teamInfo").type(JsonFieldType.STRING)
                                        .description("팀 한줄 소개"),
                                fieldWithPath("data.teamScore").type(JsonFieldType.NUMBER)
                                        .description("팀 포인트"),

                                fieldWithPath("data.teamRankRealtime").type(JsonFieldType.NUMBER)
                                        .description("팀 실시간 랭킹"),
                                fieldWithPath("data.teamLastTotalExpenses").type(JsonFieldType.NUMBER)
                                        .description("지난주 총 지출액"),
                                fieldWithPath("data.teamThisTotalExpenses").type(JsonFieldType.NUMBER)
                                        .description("이번주 총 지출액"),

                                fieldWithPath("data.teamLastEachTotalExpenses").type(JsonFieldType.ARRAY)
                                        .description("지난주 일자 별 총 지출액"),
                                fieldWithPath("data.teamLastEachTotalExpenses[].date").type(JsonFieldType.ARRAY)
                                        .description("일자"),
                                fieldWithPath("data.teamLastEachTotalExpenses[].totalAmount").type(JsonFieldType.NUMBER)
                                        .description("지출액"),

                                fieldWithPath("data.teamThisEachTotalExpenses").type(JsonFieldType.ARRAY)
                                        .description("이번주 일자 별 총 지출액"),
                                fieldWithPath("data.teamThisEachTotalExpenses[].date").type(JsonFieldType.ARRAY)
                                        .description("일자"),
                                fieldWithPath("data.teamThisEachTotalExpenses[].totalAmount").type(JsonFieldType.NUMBER)
                                        .description("지출액"),

                                fieldWithPath("data.members").type(JsonFieldType.ARRAY)
                                        .description("팀 멤버"),
                                fieldWithPath("data.members[].memberId").type(JsonFieldType.NUMBER)
                                        .description("멤버 ID"),
                                fieldWithPath("data.members[].memberNickname").type(JsonFieldType.STRING)
                                        .description("멤버 닉네임"),
                                fieldWithPath("data.members[].memberLastTotalExpenses").type(JsonFieldType.NUMBER)
                                        .description("멤버 지난주 지출 총액"),
                                fieldWithPath("data.members[].memberThisTotalExpenses").type(JsonFieldType.NUMBER)
                                        .description("멤버 이번주 지출 총액")
                        )
                        )
                );
        verify(teamService).createTeam(any(TeamCreateServiceRequest.class));

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

        TeamRequestDTO request = TeamRequestDTO.builder()
                .teamId(1L)
                .memberId(2L)
                .build();

        // Mock MemberDetailResponse 생성
        TeamMemberExpenseResponse mockMemberDetail = TeamMemberExpenseResponse.builder()
                .memberNickname("팀원닉네임")
                .build();

        // teamService.joinTeam() 메서드의 Stubbing
        given(teamService.joinTeam(any(TeamJoinServiceRequest.class)))
                .willReturn(TeamJoinResponse.builder()
                        .teamName("팀 이름")
                        .teamInfo("팀 한줄소개")
                        .teamScore(100)
                        .teamLeaderId(1L)
                        .build());

        mockMvc.perform(
                        post("/api/team/join")
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
                                        .description("팀장 ID")
                        )

                ));

        // 테스트에서 반환된 결과 확인
        verify(teamService).joinTeam(any(TeamJoinServiceRequest.class));
    }

    @DisplayName("팀 상세 조회")
    @Test
    void detailTeamInfo() throws Exception {
        // given
        Team team = mock(Team.class);
        TeamMemberExpenseResponse member = mock(TeamMemberExpenseResponse.class);
        TeamLastEachTotalResponse teamLastEachTotalResponse = mock(TeamLastEachTotalResponse.class);
        TeamThisEachTotalResponse teamThisEachTotalResponse = mock(TeamThisEachTotalResponse.class);

        given(team.getTeamId()).willReturn(1L);
        given(member.getMemberNickname()).willReturn("멤버 닉네임");
        given(teamLastEachTotalResponse.getDate()).willReturn(LocalDate.of(2024, 3, 28));
        given(teamLastEachTotalResponse.getTotalAmount()).willReturn(300000);
        given(teamThisEachTotalResponse.getDate()).willReturn(LocalDate.of(2024, 3, 28));
        given(teamThisEachTotalResponse.getTotalAmount()).willReturn(300000);

        given(teamService.detailTeamInfo(anyLong()))
                .willReturn(TeamDetailResponse.builder()
                        .teamId(1L)
                        .teamName("팀이름")
                        .teamLeaderId(1L)
                        .teamInfo("팀 한줄소개")
                        .teamScore(150)
                        .teamRankRealtime(1)
                        .teamLastTotalExpenses(30000)
                        .teamThisTotalExpenses(200000)
                        .teamLastEachTotalExpenses(List.of(teamLastEachTotalResponse))
                        .teamThisEachTotalExpenses(List.of(teamThisEachTotalResponse))
                        .members(Arrays.asList(member, member))
                        .build());

        mockMvc.perform(
                        get("/api/team/{teamId}", 1L)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("detail-team",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),

                                fieldWithPath("data").type(JsonFieldType.OBJECT)
                                        .description("응답 데이터"),
                                fieldWithPath("data.teamId").type(JsonFieldType.NUMBER)
                                        .description("팀 ID"),
                                fieldWithPath("data.teamName").type(JsonFieldType.STRING)
                                        .description("팀 명"),
                                fieldWithPath("data.teamLeaderId").type(JsonFieldType.NUMBER)
                                        .description("팀장 ID"),
                                fieldWithPath("data.teamInfo").type(JsonFieldType.STRING)
                                        .description("팀 한줄 소개"),
                                fieldWithPath("data.teamScore").type(JsonFieldType.NUMBER)
                                        .description("팀 포인트"),

                                fieldWithPath("data.teamRankRealtime").type(JsonFieldType.NUMBER)
                                        .description("팀 실시간 랭킹"),
                                fieldWithPath("data.teamLastTotalExpenses").type(JsonFieldType.NUMBER)
                                        .description("지난주 총 지출액"),
                                fieldWithPath("data.teamThisTotalExpenses").type(JsonFieldType.NUMBER)
                                        .description("이번주 총 지출액"),

                                fieldWithPath("data.teamLastEachTotalExpenses").type(JsonFieldType.ARRAY)
                                        .description("지난주 일자 별 총 지출액"),
                                fieldWithPath("data.teamLastEachTotalExpenses[].date").type(JsonFieldType.ARRAY)
                                        .description("일자"),
                                fieldWithPath("data.teamLastEachTotalExpenses[].totalAmount").type(JsonFieldType.NUMBER)
                                        .description("지출액"),

                                fieldWithPath("data.teamThisEachTotalExpenses").type(JsonFieldType.ARRAY)
                                        .description("이번주 일자 별 총 지출액"),
                                fieldWithPath("data.teamThisEachTotalExpenses[].date").type(JsonFieldType.ARRAY)
                                        .description("일자"),
                                fieldWithPath("data.teamThisEachTotalExpenses[].totalAmount").type(JsonFieldType.NUMBER)
                                        .description("지출액"),

                                fieldWithPath("data.members").type(JsonFieldType.ARRAY)
                                        .description("팀 멤버"),
                                fieldWithPath("data.members[].memberId").type(JsonFieldType.NUMBER)
                                        .description("멤버 ID"),
                                fieldWithPath("data.members[].memberNickname").type(JsonFieldType.STRING)
                                        .description("멤버 닉네임"),
                                fieldWithPath("data.members[].memberLastTotalExpenses").type(JsonFieldType.NUMBER)
                                        .description("멤버 지난주 지출 총액"),
                                fieldWithPath("data.members[].memberThisTotalExpenses").type(JsonFieldType.NUMBER)
                                        .description("멤버 이번주 지출 총액")
                        )

                ));
        verify(teamService).detailTeamInfo(anyLong());
    }

    @DisplayName("팀 정보 수정")
    @Test
    void modifyTeamInfo() throws Exception {
        Team team = mock(Team.class);

        // request
        given(team.getTeamId()).willReturn(10L);

        TeamModifyRequest request = TeamModifyRequest.builder()
                .memberId(100L)
                .teamIsAutoConfirm(false)
                .teamName("수정 전 팀이름")
                .teamLeaderId(200L)
                .teamInfo("팀 한줄소개")
                .build();
        TeamModifyResponse response = TeamModifyResponse.builder()
                .teamName("수정 후 팀이름")
                .teamLeaderId(200L)
                .teamIsAutoConfirm(false)
                .teamInfo("수정 후 팀 한줄소개")
                .build();

        given(teamService.modifyTeam(eq(10L), any(TeamModifyRequest.class))).willReturn(response);

        mockMvc.perform(
                        patch("/api/team/{teamId}", 10L)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("modify-team",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("memberId").type(JsonFieldType.NUMBER)
                                        .description("요청 보내는 유저 ID"),
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
                                        .description("수정 후 팀 명"),
                                fieldWithPath("data.teamLeaderId").type(JsonFieldType.NUMBER)
                                        .description("수정 후 팀장 ID"),
                                fieldWithPath("data.teamIsAutoConfirm").type(JsonFieldType.BOOLEAN)
                                        .description("수정 후 자동 가입 승인 여부"),
                                fieldWithPath("data.teamInfo").type(JsonFieldType.STRING)
                                        .description("수정 후 팀 소개")
                        )
                ));
        verify(teamService).modifyTeam(eq(10L), any(TeamModifyRequest.class));

    }

    @DisplayName("팀 탈퇴")
    @Test
    void leaveTeam() throws Exception {
        Team team = mock(Team.class);
        ChatRoom chatRoom = mock(ChatRoom.class);
        Member member = mock(Member.class);

        SimpleTeamDto request = SimpleTeamDto.builder()
                .teamId(10L)
                .memberId(10L)
                .build();

        doNothing().when(teamService).leaveTeam(any(SimpleTeamDto.class));

        mockMvc.perform(post("/api/team/leave")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("leave-team",
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

                                fieldWithPath("data").type(JsonFieldType.STRING)
                                        .description("탈퇴 완료 메시지")
                        )

                ));

        verify(teamService).leaveTeam(any(SimpleTeamDto.class));
    }

    @DisplayName("팀 삭제")
    @Test
    void deleteTeam() throws Exception {
        Team team = mock(Team.class);
        ChatRoom chatRoom = mock(ChatRoom.class);
        Member member = mock(Member.class);

        SimpleTeamDto request = SimpleTeamDto.builder()
                .teamId(10L)
                .memberId(10L)
                .build();

        doNothing().when(teamService).deleteTeam(any(SimpleTeamDto.class));

        mockMvc.perform(delete("/api/team")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("delete-team",
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

                                fieldWithPath("data").type(JsonFieldType.STRING)
                                        .description("삭제 완료 메시지")
                        )

                ));

        verify(teamService).deleteTeam(any(SimpleTeamDto.class));
    }

    @DisplayName("팀원 추방")
    @Test
    void banishMember() throws Exception {
        Team team = mock(Team.class);
        ChatRoom chatRoom = mock(ChatRoom.class);
        Member leader = mock(Member.class);
        Member targetMember = mock(Member.class);

        TeamBanishRequest request = TeamBanishRequest.builder()
                .teamId(1L)
                .teamLeaderId(1L)
                .targetMemberId(2L)
                .build();

        doNothing().when(teamService).banishMember(any(TeamBanishRequest.class));

        mockMvc.perform(
                        post("/api/team/ban")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("banish-member",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("teamId").type(JsonFieldType.NUMBER)
                                        .description("팀 ID"),
                                fieldWithPath("teamLeaderId").type(JsonFieldType.NUMBER)
                                        .description("요청 보내는 유저 ID"),
                                fieldWithPath("targetMemberId").type(JsonFieldType.NUMBER)
                                        .description("추방 할 유저 ID")
                        ), responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),

                                fieldWithPath("data").type(JsonFieldType.STRING)
                                        .description("추방 완료 메시지")
                        )

                ));

        verify(teamService).banishMember(any(TeamBanishRequest.class));

    }

    @DisplayName("팀명 중복 체크")
    @Test
    void validTeamName() throws Exception {
        // given
        Team team = mock(Team.class);

        when(teamService.validTeamName(anyString())).thenReturn(false);

        mockMvc.perform(
                        post("/api/team/create/{keyword}", "teamName1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("valid-team-name",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),

                                fieldWithPath("data").type(JsonFieldType.BOOLEAN)
                                        .description("중복 여부")
                        )

                ));

        verify(teamService).validTeamName(anyString());

    }

    @DisplayName("팀 가입 대기 리스트 조회")
    @Test
    void waitingList() throws Exception {
        // given
        Team team = mock(Team.class);
        Member member = mock(Member.class);

        TeamRequestDTO request = TeamRequestDTO.builder()
                .teamId(1L)
                .memberId(3L)
                .build();

        TeamWaitingListResponse response = TeamWaitingListResponse.builder()
                .memberId(3L)
                .memberNickname("대기자 닉네임")
                .memberMostCategory("문화")
                .build();

        List<TeamWaitingListResponse> responseList = List.of(response, response);

        when(teamService.waitingList(any(TeamRequestDTO.class)))
                .thenReturn(responseList);

        mockMvc.perform(
                        post("/api/team/waitingList")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("waiting-list",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestFields(
                                        fieldWithPath("teamId").type(JsonFieldType.NUMBER)
                                                .description("팀 ID"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER)
                                                .description("리스트 보려는 유저(팀장) ID")
                                ), responseFields(
                                        fieldWithPath("code").type(JsonFieldType.NUMBER)
                                                .description("코드"),
                                        fieldWithPath("status").type(JsonFieldType.STRING)
                                                .description("상태"),
                                        fieldWithPath("message").type(JsonFieldType.STRING)
                                                .description("메시지"),

                                        fieldWithPath("data").type(JsonFieldType.ARRAY)
                                                .description("응답 데이터"),
                                        fieldWithPath("data.[].memberId").type(JsonFieldType.NUMBER)
                                                .description("유저 ID"),
                                        fieldWithPath("data.[].memberNickname").type(JsonFieldType.STRING)
                                                .description("유저 닉네임"),
                                        fieldWithPath("data.[].memberMostCategory").type(JsonFieldType.STRING)
                                                .description("유저 최대 지출 카테고리")
                                )
                        )

                );
        verify(teamService).waitingList(any(TeamRequestDTO.class));

    }

    @DisplayName("다른 팀 상세조회")
    @Test
    void detailOtherTeamInfo() throws Exception {
        // given
        Team team = mock(Team.class);
        Member member = mock(Member.class);

        TeamOtherDetailResponse otherTeamInfo = TeamOtherDetailResponse.builder()
                .teamName("팀 이름")
                .teamLeaderNickname("리더 닉네임")
                .lastRank(2)
                .teamInfo("팀 한줄 소개")
                .build();

        when(teamService.detailOtherTeamInfo(anyLong()))
                .thenReturn(otherTeamInfo);

        mockMvc.perform(
                        get("/api/team/detail/{teamId}", 1L)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("detail-other-team",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
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
                                        .description("팀 한줄 소개"),
                                fieldWithPath("data.teamLeaderNickname").type(JsonFieldType.STRING)
                                        .description("팀장 닉네임"),
                                fieldWithPath("data.lastRank").type(JsonFieldType.NUMBER)
                                        .description("가장 마지막 랭크 내역")
                        )
                ));
        verify(teamService).detailOtherTeamInfo(anyLong());

    }

    @DisplayName("가입 승인")
    @Test
    void approveMember() throws Exception {
        // given
        Team team = mock(Team.class);
        Member member = mock(Member.class);

        TeamRequestDTO request = TeamRequestDTO.builder()
                .teamId(1L)
                .memberId(1L)
                .build();

        doNothing().when(teamService).approveMember(any(TeamRequestDTO.class));

        mockMvc.perform(
                        post("/api/team/approve")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(
                        document("approve-member",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestFields(
                                        fieldWithPath("teamId").type(JsonFieldType.NUMBER)
                                                .description("팀 ID"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER)
                                                .description("승인 대기 유저 ID")
                                ), responseFields(

                                        fieldWithPath("code").type(JsonFieldType.NUMBER)
                                                .description("코드"),
                                        fieldWithPath("status").type(JsonFieldType.STRING)
                                                .description("상태"),
                                        fieldWithPath("message").type(JsonFieldType.STRING)
                                                .description("메시지"),
                                        fieldWithPath("data").type(JsonFieldType.STRING)
                                                .description("승인 완료 메시지")
                                )
                        ));
        verify(teamService).approveMember(any(TeamRequestDTO.class));
    }

    @DisplayName("가입 거절")
    @Test
    void rejectMember() throws Exception {
        // given
        Team team = mock(Team.class);
        Member member = mock(Member.class);

        TeamRequestDTO request = TeamRequestDTO.builder()
                .teamId(1L)
                .memberId(1L)
                .build();

        doNothing().when(teamService).rejectMember(any(TeamRequestDTO.class));

        mockMvc.perform(
                        post("/api/team/reject")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(
                        document("reject-member",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestFields(
                                        fieldWithPath("teamId").type(JsonFieldType.NUMBER)
                                                .description("팀 ID"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER)
                                                .description("승인 대기 유저 ID")
                                ), responseFields(

                                        fieldWithPath("code").type(JsonFieldType.NUMBER)
                                                .description("코드"),
                                        fieldWithPath("status").type(JsonFieldType.STRING)
                                                .description("상태"),
                                        fieldWithPath("message").type(JsonFieldType.STRING)
                                                .description("메시지"),
                                        fieldWithPath("data").type(JsonFieldType.STRING)
                                                .description("거절 완료 메시지")
                                )
                        )
                );
        verify(teamService).rejectMember(any(TeamRequestDTO.class));

    }

    private Member createMember(String memberEmail, String memberNickname, LocalDate memberBirthDate) {
        return Member.builder()
                .memberEmail(memberEmail)
                .memberPassword("1234")
                .memberName("김김짠돌")
                .memberNickname(memberNickname)
                .memberBirthDate(memberBirthDate)
                .build();


    }

}