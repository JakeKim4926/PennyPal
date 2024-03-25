package com.ssafy.pennypal.domain.team.controller;

import com.ssafy.pennypal.bank.service.api.BankServiceAPIImpl;
import com.ssafy.pennypal.domain.chat.service.ChatService;
import com.ssafy.pennypal.domain.team.dto.request.TeamCreateRequest;
import com.ssafy.pennypal.domain.team.dto.request.TeamJoinRequest;
import com.ssafy.pennypal.domain.team.dto.request.TeamLeaveRequest;
import com.ssafy.pennypal.domain.team.dto.request.TeamModifyRequest;
import com.ssafy.pennypal.domain.team.dto.response.*;
import com.ssafy.pennypal.domain.team.service.TeamService;
import com.ssafy.pennypal.global.common.api.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/team")
public class TeamController {

    private final TeamService teamService;
    private final ChatService chatService;

    private final BankServiceAPIImpl bankServiceAPI;

    /**
     * note : 2.1 팀 생성 ( + 팀 채팅방 생성 )
     */
    @PostMapping("/create")
    public ApiResponse<TeamCreateResponse> createTeam(@Valid @RequestBody TeamCreateRequest request) {

        // 팀 생성
        TeamCreateResponse result = teamService.createTeam(request.toServiceRequest());

        // 팀 채팅방 생성
        chatService.createChatRoom(request.getTeamLeaderId());

        return ApiResponse.ok(result);

    }

    /**
     * note : 매주 월요일 오전 12시에 주간 랭킹 업데이트
     */
    @Scheduled(cron = "0 0 0 * * MON")
    public void autoRank() {
        teamService.calculateTeamScore();
        teamService.RankTeamScore();
    }

    /**
     * note : 2.2 팀 주간 랭킹 조회
     */
    @GetMapping("/rank/weekly")
    public ApiResponse<List<TeamRankHistoryResponse>> weeklyTeamRanking(){

        return ApiResponse.ok(teamService.rankHistoriesForWeeks());
    }

    /**
     * note : 2.2.1 팀 실시간 랭킹 조회
     */
    @GetMapping("/rank/realtime")
    public ApiResponse<List<TeamRankResponse>> realtimeTeamRanking() {

        // 팀 점수 계산
        teamService.calculateTeamScore();

        // 등수 계산
        List<TeamRankResponse> result = teamService.RankTeamRealTimeScore();

        return ApiResponse.ok(result);
    }

    /**
     * note : 2.3 팀 전체 조회 + 검색 (팀이름)
     */
    @GetMapping
    public ApiResponse<List<TeamSearchResponse>> searchTeamList(
            @RequestParam(name = "keyword", required = false) String teamName
    ){

        return ApiResponse.ok(teamService.searchTeamList(teamName));
    }

    /**
     * note : 2.4 팀 상세 조회
     */
    @GetMapping("/{teamId}")
    public ApiResponse<TeamDetailResponse> detailTeamInfo(@PathVariable Long teamId){

        return ApiResponse.ok(teamService.detailTeamInfo(teamId));
    }

    /**
     * note : 2.5 팀 정보 수정
     */
    @PatchMapping("/{teamId}")
    public ApiResponse<TeamModifyResponse> modifyTeam(@PathVariable("teamId") Long teamId,
                                                      @RequestBody TeamModifyRequest request){

        return ApiResponse.ok(teamService.modifyTeam(teamId, request));
    }

    /**
     * note : 2.5.2 팀 가입 ( + 팀 채팅방 초대 )
     */
    @PostMapping("/join")
    public ApiResponse<TeamJoinResponse> joinTeam(@RequestBody TeamJoinRequest request) {

        TeamJoinResponse result = teamService.joinTeam(request.toServiceRequest());

        // 팀 채팅방 초대
        chatService.inviteChatRoom(request.getTeamId(), request.getMemberId());

        return ApiResponse.ok(result);

    }

    /**
     * note : 2.5.3 팀 탈퇴
     * todo : 응답값 상의 후 수정
     */
    @PostMapping("leave")
    public ApiResponse leaveTeam(@RequestBody TeamLeaveRequest request){

        teamService.leaveTeam(request);

        return ApiResponse.ok(null);
    }

}
