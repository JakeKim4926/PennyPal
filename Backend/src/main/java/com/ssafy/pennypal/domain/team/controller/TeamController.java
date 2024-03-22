package com.ssafy.pennypal.domain.team.controller;

import com.ssafy.pennypal.domain.team.dto.request.TeamCreateRequest;
import com.ssafy.pennypal.domain.team.dto.request.TeamJoinRequest;
import com.ssafy.pennypal.domain.team.dto.request.TeamJoinServiceRequest;
import com.ssafy.pennypal.domain.team.dto.response.TeamCreateResponse;
import com.ssafy.pennypal.domain.team.dto.response.TeamJoinResponse;
import com.ssafy.pennypal.domain.team.service.TeamService;
import com.ssafy.pennypal.global.common.api.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/team")
public class TeamController {

    private final TeamService teamService;


    /**
     * note : 2.1 팀 생성
     */
    @PostMapping("/create")
    public ApiResponse<TeamCreateResponse> createTeam(@Valid @RequestBody TeamCreateRequest request){

        return ApiResponse.ok(teamService.createTeam(request.toServiceRequest()));

    }

    /**
     * note : 2.5 팀 가입
     */
    @PostMapping("/{teamId}")
    public ApiResponse<TeamJoinResponse> joinTeam(@PathVariable Long teamId, Long memberId){

        TeamJoinRequest joinRequest = TeamJoinRequest.builder()
                .teamId(teamId)
                .memberId(memberId)
                .build();

        return ApiResponse.ok(teamService.joinTeam(joinRequest.toServiceRequest()));

    }

}
