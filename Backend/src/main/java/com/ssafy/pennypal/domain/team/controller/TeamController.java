package com.ssafy.pennypal.domain.team.controller;

import com.ssafy.pennypal.bank.service.api.BankServiceAPIImpl;
import com.ssafy.pennypal.domain.chat.service.ChatService;
import com.ssafy.pennypal.domain.team.dto.request.TeamBanishRequest;
import com.ssafy.pennypal.domain.team.dto.request.TeamCreateRequest;
import com.ssafy.pennypal.domain.team.dto.request.TeamRequestDTO;
import com.ssafy.pennypal.domain.team.dto.SimpleTeamDto;
import com.ssafy.pennypal.domain.team.dto.request.TeamModifyRequest;
import com.ssafy.pennypal.domain.team.dto.response.*;
import com.ssafy.pennypal.domain.team.service.TeamService;
import com.ssafy.pennypal.global.common.api.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
    public ApiResponse<String> createTeam(@Valid @RequestBody TeamCreateRequest request) {

        // 팀 생성
        teamService.createTeam(request.toServiceRequest());

        // 팀 채팅방 생성
        chatService.createChatRoom(request.getTeamLeaderId());

        return ApiResponse.ok("팀 생성 완료");

    }

    /**
     * note : 팀명 중복 체크
     */
    @PostMapping("/create/{keyword}")
    public ApiResponse<Boolean> validTeamName(@PathVariable String keyword){

        Boolean result = teamService.validTeamName(keyword);

        if(result){
            return ApiResponse.of(HttpStatus.CONFLICT, "NO",result );
        }else{
            return ApiResponse.ok(result);
        }
    }


    /**
     * note : 매주 월요일 오전 12시에 주간 랭킹 업데이트
     */
//    @Scheduled(cron = "00 00 00 * * MON")
    @PostMapping("/rank")
    public void autoRankWeekly() {
        teamService.calculateTeamScore();
        teamService.RankTeamScore();

        // 모든 유저의 출석 일수 초기화
        teamService.resetMemberAttendance();
    }

    /**
     * note : 2.2 팀 주간 랭킹 조회
     */
    @GetMapping("/rank/weekly/{teamId}")
    public ApiResponse<Page<TeamRankWeeklyResponse>> weeklyTeamRanking(
            @PathVariable("teamId") Long teamId,
            @PageableDefault(page = 0, size = 6, direction = Sort.Direction.ASC)
            Pageable pageable){

        return ApiResponse.ok(teamService.rankOfWeeks(teamId, pageable));
    }

    /**
     * note : 매 시 정각에 실시간 랭킹 업데이트
     */
//    @Scheduled(cron = "0 0 * * * *")
    @PostMapping("/rankRealtime")
    public void autoRankRealtime() {

        // 팀 점수 계산
        teamService.calculateTeamScore();

        // 팀 실시간 등수 계산
        teamService.RankRealTimeScore();
    }

    /**
     * note : 2.2.1 팀 실시간 랭킹 조회
     */
    @GetMapping("/rank/realtime/{teamId}")
    public ApiResponse<Page<TeamRankRealtimeResponse>> realtimeTeamRanking(
            @PathVariable("teamId") Long teamId,
            @PageableDefault(page = 0, size = 6, direction = Sort.Direction.ASC)
            Pageable pageable) {

        return ApiResponse.ok(teamService.rankOfRealtime(teamId, pageable));
    }

    /**
     * note : 2.3 팀 전체 조회 + 검색 (팀이름)
     */
    @GetMapping
    public ApiResponse<Page<TeamSearchResponse>> searchTeamList(
            @RequestParam(name = "keyword", required = false) String teamName,
            @PageableDefault(page = 0, size = 4, sort = "teamName" , direction = Sort.Direction.ASC)
            Pageable pageable
    ){

        return ApiResponse.ok(teamService.searchTeamList(teamName, pageable));
    }

    /**
     * note : 2.4 팀 상세 조회 (내 팀)
     */
    @GetMapping("/{memberId}")
    public ApiResponse<TeamDetailResponse> detailTeamInfo(@PathVariable Long memberId){

        return ApiResponse.ok(teamService.detailTeamInfo(memberId));
    }

    /**
     * note : 팀 상세 조회 (다른 팀)
     */
    @GetMapping("detail/{teamId}")
    public ApiResponse<TeamOtherDetailResponse> detailOtherTeamInfo(@PathVariable Long teamId){

        return ApiResponse.ok(teamService.detailOtherTeamInfo(teamId));
    }

    /**
     * note : 수동 가입인 경우 가입 대기 유저 조회
     */
    @GetMapping("/waitingList")
    public ApiResponse<List<TeamWaitingListResponse>> waitingList(@RequestBody TeamRequestDTO request){

        return ApiResponse.ok(teamService.waitingList(request));
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
     * note : 2.5.1 팀원 추방
     */
    @PostMapping("/ban")
    public ApiResponse<String> banishMember(@RequestBody TeamBanishRequest request){

        teamService.banishMember(request);

        return ApiResponse.ok("추방 완료");
    }

    /**
     * note : 2.5.2 팀 가입 ( + 팀 채팅방 초대 )
     */
    @PostMapping("/join")
    public ApiResponse<TeamJoinResponse> joinTeam(@RequestBody TeamRequestDTO request) {

        TeamJoinResponse result = teamService.joinTeam(request.toServiceRequest());

        if(result == null){
            // 수동 승인일 때
            return ApiResponse.of(HttpStatus.ACCEPTED, "Hold", null);
        }else{

        // 팀 채팅방 초대
        chatService.inviteChatRoom(request.getTeamId(), request.getMemberId());

        return ApiResponse.ok(result);
}
    }

    /**
     * note : 2.5.3 팀 탈퇴
     */
    @PostMapping("leave")
    public ApiResponse<String> leaveTeam(@RequestBody SimpleTeamDto request){

        teamService.leaveTeam(request);

        return ApiResponse.ok("탈퇴 완료");
    }

    /**
     * note : 2.6 팀 삭제
     */
    @DeleteMapping
    public ApiResponse<String> deleteTeam(@RequestBody SimpleTeamDto request){

        teamService.deleteTeam(request);

        return ApiResponse.ok("삭제 완료");
    }

}
