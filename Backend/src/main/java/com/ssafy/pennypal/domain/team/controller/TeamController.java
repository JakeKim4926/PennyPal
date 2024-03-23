package com.ssafy.pennypal.domain.team.controller;

import com.ssafy.pennypal.bank.dto.controller.response.UserAccountResponseControllerDTO;
import com.ssafy.pennypal.bank.dto.service.common.CommonHeaderRequestDTO;
import com.ssafy.pennypal.bank.dto.service.request.GetUserAccountListServiceRequestDTO;
import com.ssafy.pennypal.bank.service.api.BankServiceAPIImpl;
import com.ssafy.pennypal.bank.service.api.IBankServiceAPI;
import com.ssafy.pennypal.bank.service.db.IBankServiceDB;
import com.ssafy.pennypal.domain.team.dto.request.TeamCreateRequest;
import com.ssafy.pennypal.domain.team.dto.request.TeamJoinRequest;
import com.ssafy.pennypal.domain.team.dto.request.TeamJoinServiceRequest;
import com.ssafy.pennypal.domain.team.dto.response.TeamCreateResponse;
import com.ssafy.pennypal.domain.team.dto.response.TeamJoinResponse;
import com.ssafy.pennypal.domain.team.dto.response.TeamRankHistoryResponse;
import com.ssafy.pennypal.domain.team.dto.response.TeamRankResponse;
import com.ssafy.pennypal.domain.team.entity.TeamRankHistory;
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

    private final BankServiceAPIImpl bankServiceAPI;

    /**
     * note : 2.1 팀 생성
     */
    @PostMapping("/create")
    public ApiResponse<TeamCreateResponse> createTeam(@Valid @RequestBody TeamCreateRequest request) {

        return ApiResponse.ok(teamService.createTeam(request.toServiceRequest()));

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
     * note : 2.5 팀 가입
     */
    @PostMapping("/{teamId}")
    public ApiResponse<TeamJoinResponse> joinTeam(@PathVariable Long teamId, Long memberId) {

        TeamJoinRequest joinRequest = TeamJoinRequest.builder()
                .teamId(teamId)
                .memberId(memberId)
                .build();

        return ApiResponse.ok(teamService.joinTeam(joinRequest.toServiceRequest()));

    }

}
