package com.ssafy.pennypal.domain.team.service;

import com.ssafy.pennypal.bank.dto.service.common.CommonHeaderRequestDTO;
import com.ssafy.pennypal.bank.dto.service.request.AccountTransactionRequestServiceDTO;
import com.ssafy.pennypal.bank.dto.service.request.GetUserAccountListServiceRequestDTO;
import com.ssafy.pennypal.bank.dto.service.response.AccountTransactionListResponseServiceDTO;
import com.ssafy.pennypal.bank.dto.service.response.AccountTransactionResponseServiceDTO;
import com.ssafy.pennypal.bank.dto.service.response.UserAccountListResponseServiceDTO;
import com.ssafy.pennypal.bank.dto.service.response.UserBankAccountsResponseServiceDTO;
import com.ssafy.pennypal.bank.service.api.BankServiceAPIImpl;
import com.ssafy.pennypal.domain.chat.entity.ChatRoom;
import com.ssafy.pennypal.domain.chat.repository.IChatRoomRepository;
import com.ssafy.pennypal.domain.member.dto.response.MemberExpensesDetailResponse;
import com.ssafy.pennypal.domain.member.entity.Member;
import com.ssafy.pennypal.domain.member.entity.Expense;
import com.ssafy.pennypal.domain.member.repository.IMemberRepository;
import com.ssafy.pennypal.domain.team.dto.request.TeamBanishRequest;
import com.ssafy.pennypal.domain.team.dto.request.TeamCreateServiceRequest;
import com.ssafy.pennypal.domain.team.dto.request.TeamJoinServiceRequest;
import com.ssafy.pennypal.domain.team.dto.SimpleTeamDto;
import com.ssafy.pennypal.domain.team.dto.request.TeamModifyRequest;
import com.ssafy.pennypal.domain.team.dto.response.*;
import com.ssafy.pennypal.domain.team.entity.Team;
import com.ssafy.pennypal.domain.team.entity.TeamRankHistory;
import com.ssafy.pennypal.domain.team.repository.ITeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeamService {

    private final ITeamRepository teamRepository;
    private final IMemberRepository memberRepository;
    private final IChatRoomRepository chatRoomRepository;

    private static final String SSAFY_BANK_API_KEY = System.getenv("SSAFY_BANK_API_KEY");
    private final BankServiceAPIImpl bankServiceAPI;

    private static final LocalDate TODAY = LocalDate.now();
    private static final LocalDate MONDAY_OF_THIS_WEEK = TODAY.with(DayOfWeek.MONDAY);
    private static final LocalDate MONDAY_OF_LAST_WEEK = MONDAY_OF_THIS_WEEK.minusDays(7);
    private static final LocalDate SUNDAY_OF_LAST_WEEK = MONDAY_OF_THIS_WEEK.minusDays(1);
    private static final LocalDate SUNDAY_OF_THIS_WEEK = TODAY.with(DayOfWeek.SUNDAY);
    private static final LocalDate MONDAY_OF_NEXT_WEEK = MONDAY_OF_THIS_WEEK.plusDays(7);
    private static final LocalDate SUNDAY_OF_TWO_LAST_WEEK = SUNDAY_OF_THIS_WEEK.minusDays(14);

    @Transactional
    public TeamCreateResponse createTeam(TeamCreateServiceRequest request) {
        // 유저 정보 가져오기
        Member member = getMember(request.getTeamLeaderId());

        if (member == null) {
            throw new IllegalArgumentException("유저를 찾을 수 없습니다.");
        } else {

            // 이미 존재하는 팀명이라면 예외 발생
            if (teamRepository.findByTeamName(request.getTeamName()).isPresent()) {
                throw new IllegalArgumentException("이미 사용 중인 팀명입니다.");
            }

            // 유저가 포함된 팀 있는지 확인
            if (member.getTeam() != null) {
                throw new IllegalArgumentException("한 개의 팀에만 가입 가능합니다.");
            }
        }
        // 팀 생성
        Team team = Team.builder()
                .teamName(request.getTeamName())
                .teamIsAutoConfirm(request.getTeamIsAutoConfirm())
                .teamLeaderId(request.getTeamLeaderId())
                .member(member)
                .build();

        // 팀 저장
        Team savedTeam = teamRepository.save(team);

        // 유저 team 정보 수정
        member.setTeam(team);
        memberRepository.save(member);

        List<TeamMemberExpenseResponse> members = team.getMembers().stream()
                .filter(Objects::nonNull)
                .map(m -> {
                    List<MemberExpensesDetailResponse> lastWeekExpenses = member.getMemberExpensesOfLastWeek().stream()
                            .map(expense -> new MemberExpensesDetailResponse(expense.getExpenseDate(), expense.getExpenseAmount()))
                            .collect(Collectors.toList());
                    List<MemberExpensesDetailResponse> thisWeekExpenses = member.getMemberExpensesOfThisWeek().stream()
                            .map(expense -> new MemberExpensesDetailResponse(expense.getExpenseDate(), expense.getExpenseAmount()))
                            .collect(Collectors.toList());
                    return new TeamMemberExpenseResponse(
                            member.getMemberNickname(),
                            lastWeekExpenses,
                            thisWeekExpenses);
                })
                .collect(Collectors.toList());


        return TeamCreateResponse.of(savedTeam, members);
    }

    @Transactional
    public TeamJoinResponse joinTeam(TeamJoinServiceRequest request) {

        // 팀 정보 가져오기
        Team team = getTeam(request.getTeamId());

        if (team != null) {

            // 유저 정보 조회
            Member member = getMember(request.getMemberId());

            // 팀 인원 6명이면 예외 발생
            if (team.getMembers().size() == 6) {
                throw new IllegalArgumentException("팀 인원이 가득 찼습니다.");
            }

            // 팀 구성원에 포함 돼 있는지 확인
            if (team.getMembers().contains(member)) {
                throw new IllegalArgumentException("이미 가입한 팀입니다.");
            } else {
                // 이미 다른 팀의 구성원인지 확인
                if (member.getTeam() != null) {
                    throw new IllegalArgumentException("이미 가입된 팀이 있습니다.");
                }
            }

            // 추방된 유저라면 가입 불가
            if (team.getTeamBanishedList().contains(member)) {
                throw new IllegalArgumentException("추방 당한 팀에는 가입할 수 없습니다.");
            }

            // 팀 자동승인 여부에 따라...
            if (team.getTeamIsAutoConfirm()) {
                // 자동 승인이라면 바로 추가
                team.getMembers().add(member);
                member.setTeam(team);
                teamRepository.save(team);
                memberRepository.save(member);
            } else {
                // 수동 승인이라면 대기 리스트에 추가하고 예외 던져주기
                team.getTeamWaitingList().add(member);
                member.setMemberWaitingTeam(team);
                throw new IllegalArgumentException("가입 요청 완료 메시지를 위한 에러 발생");
            }

        } else {
            throw new IllegalArgumentException("팀 정보를 찾을 수 없습니다.");
        }


        return TeamJoinResponse.builder()
                .teamName(team.getTeamName())
                .teamInfo(team.getTeamInfo())
                .teamScore(team.getTeamScore())
                .teamLeaderId(team.getTeamLeaderId())
                .build();
    }

    @Transactional
    public void calculateTeamScore() {

        List<Team> teams = teamRepository.findAll();

        for (Team team : teams) {

            // 4인 미만인 팀은 점수를 계산하지 않는다.
            if (team.getMembers().size() < 4) {
                continue;
            } else {

                //팀 점수 초기화
                int teamScore = 0;

                List<Member> members = team.getMembers().stream()
                        .filter(Objects::nonNull)
                        .toList();

                // 멤버의 계좌 목록 조회
                for (Member member : members) {

                    String memberBankApi = member.getMemberBankApi();

                    // 공통 헤더 정보 설정
                    CommonHeaderRequestDTO commonHeaderRequestDTO = CommonHeaderRequestDTO.builder()
                            .apiName("inquireAccountList")
                            .apiKey(SSAFY_BANK_API_KEY)
                            .userKey(memberBankApi)
                            .build();

                    // 계좌 목록 조회 요청 객체 생성
                    GetUserAccountListServiceRequestDTO requestDTO = GetUserAccountListServiceRequestDTO.builder()
                            .header(commonHeaderRequestDTO)
                            .build();

                    // 계좌 목록 조회 API 호출
                    UserBankAccountsResponseServiceDTO responseDTO = bankServiceAPI.getUserAccountList(requestDTO);

                    // 각 계좌의 지난 주, 이번 주 거래 내역 조회
                    for (UserAccountListResponseServiceDTO account : responseDTO.getREC()) {
                        // 계좌 거래 내역 조회를 위한 요청 객체 생성
                        AccountTransactionRequestServiceDTO transactionRequestDTO = AccountTransactionRequestServiceDTO.builder()
                                .header(commonHeaderRequestDTO)
                                .bankCode(account.getBankCode()) // 은행 코드
                                .accountNo(account.getAccountNo()) // 계좌 번호

                                // LocalDate -> String
                                .startDate(MONDAY_OF_LAST_WEEK.format(DateTimeFormatter.ofPattern("yyyyMMdd")))
                                .endDate(SUNDAY_OF_THIS_WEEK.format(DateTimeFormatter.ofPattern("yyyyMMdd")))

                                .transactionType("D") // 출금 내역만
                                .orderByType("default")
                                .build();

                        // 계좌 거래 내역 조회 API 호출
                        AccountTransactionListResponseServiceDTO transactionListResponseDTO
                                = bankServiceAPI.getUserAccountTransaction(transactionRequestDTO);

                        // 유저의 2주 동안의 지출 날짜, 지출 금액, 해당 유저를 리스트로 담기
                        List<Expense> allExpenses = new ArrayList<>();

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");

                        for (AccountTransactionResponseServiceDTO transaction : transactionListResponseDTO.getREC().getList()) {
                            allExpenses.add(new Expense(LocalDate.parse(transaction.getTransactionDate(), formatter),
                                    parseInt(transaction.getTransactionBalance()), member));
                        }

                        // 지난 주 , 이번 주 거래 내역 구분해서 저장
                        calculateLastWeekExpenses(allExpenses);
                        calculateThisWeekExpenses(allExpenses);
                    }

                } // member

                // 팀원들의 지난 주와 이번 주 지출 내역을 모두 더하기
                Double lastWeekTotalExpenses = team.getMembers().stream()
                        .filter(Objects::nonNull)
                        .flatMap(member -> member.getMemberExpensesOfLastWeek().stream()) // 지난 주 지출 내역을 가져옴
                        .mapToDouble(Expense::getExpenseAmount) // 지출 금액을 가져와서 double 형태로 매핑
                        .sum();

                Double thisWeekTotalExpenses = team.getMembers().stream()
                        .filter(Objects::nonNull)
                        .flatMap(member -> member.getMemberExpensesOfThisWeek().stream()) // 이번 주 지출 내역을 가져옴
                        .mapToDouble(Expense::getExpenseAmount) // 지출 금액을 가져와서 double 형태로 매핑
                        .sum();

                // 팀원들의 출석 횟수 모두 더하기
                Double totalAttendance = team.getMembers().stream()
                        .filter(Objects::nonNull)
                        .mapToDouble(Member::getMemberAttendance)
                        .sum();

                // 절약 점수 계산
                Integer savingScore = calculateSavingScore(lastWeekTotalExpenses, thisWeekTotalExpenses);

                // 출석 점수 계산
                Integer attendanceScore = calculateAttendanceScore(totalAttendance, team.getMembers().size());

                // 팀 점수 저장
                team.setTeamScore(savingScore + attendanceScore);
            }
        }

    }

    @Transactional
    public void RankTeamScore() {

        List<Team> teams = teamRepository.findAll();

        // 현재 날짜
        LocalDate today = LocalDate.now();

        // 팀 점수에 따라 내림차순 정렬
        List<TeamRankResponse> rankedTeams = teams.stream()
                .map(team -> new TeamRankResponse(team.getTeamId(), team.getTeamName(), team.getTeamScore(), 0))
                .sorted(Comparator.comparing(TeamRankResponse::getTeamScore).reversed())
                .collect(Collectors.toList());

        // 등수 계산

        // 등수
        int rankNum = 1;

        // 이전 팀의 점수
        int previousScore = rankedTeams.isEmpty() ? 0 : rankedTeams.get(0).getTeamScore();

        // 동점인 팀이 있는지?
        int sameScoreCount = 1;

        for (int i = 0; i < rankedTeams.size(); i++) {
            TeamRankResponse currentTeamResponse = rankedTeams.get(i);

            // 순위를 매길 팀
            Team currentTeam = teams.stream()
                    .filter(t -> t.getTeamId().equals(currentTeamResponse.getTeamId()))
                    .findFirst()
                    .orElse(null);

            if (currentTeam == null) continue;

            if (currentTeamResponse.getTeamScore() == previousScore) {

                // 이전 팀과 점수가 같다면, 같은 등수로 저장
                currentTeamResponse.setTeamRankNum(rankNum);

            } else {
                // 점수가 다르면, 이전에 동점이었던 팀 수 만큼 현재 팀의 등수는 내려감
                rankNum += sameScoreCount;
                currentTeamResponse.setTeamRankNum(rankNum);
                sameScoreCount = 0;
            }

            // 동점 팀 수 +1
            sameScoreCount++;

            //다음 팀으로 넘어가기 전에 현재 팀의 점수 저장
            previousScore = currentTeamResponse.getTeamScore();

            // 팀 랭킹 기록 저장
            TeamRankHistory newRankHistory = TeamRankHistory.builder()
                    .team(currentTeam)
                    .rankDate(today)
                    .rankNum(rankNum)
                    .build();

            currentTeam.getTeamRankHistories().add(newRankHistory);
            teamRepository.save(currentTeam);
        }

    }

    public List<TeamRankHistoryResponse> rankHistoriesForWeeks() {

        List<Team> teams = teamRepository.findAll();

        // 월요일에 집계된 등수 리턴
        return teams.stream()
                .flatMap(team -> team.getTeamRankHistories().stream())
                .filter(history -> history.getRankDate().isEqual(MONDAY_OF_THIS_WEEK)) // 이번주 월요일에 해당하는 기록만 필터링
                .map(history -> new TeamRankHistoryResponse(history.getTeam().getTeamName(), history.getRankDate(), history.getRankNum()))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<TeamRankResponse> RankTeamRealTimeScore() {

        List<Team> teams = teamRepository.findAll();

        // 팀 점수에 따라 내림차순 정렬
        List<TeamRankResponse> rankedTeams = teams.stream()
                .map(team -> new TeamRankResponse(team.getTeamId(), team.getTeamName(), team.getTeamScore(), 0))
                .sorted(Comparator.comparing(TeamRankResponse::getTeamScore).reversed())
                .collect(Collectors.toList());

        // 등수 계산
        int rankNum = 1;
        int previousScore = rankedTeams.isEmpty() ? 0 : rankedTeams.get(0).getTeamScore();
        int sameScoreCount = 1;

        for (int i = 0; i < rankedTeams.size(); i++) {
            TeamRankResponse currentTeamResponse = rankedTeams.get(i);
            Team currentTeam = teams.stream()
                    .filter(t -> t.getTeamId().equals(currentTeamResponse.getTeamId()))
                    .findFirst()
                    .orElse(null);

            if (currentTeam == null) continue;

            if (currentTeamResponse.getTeamScore() == previousScore) {
                currentTeamResponse.setTeamRankNum(rankNum);
            } else {
                rankNum += sameScoreCount;
                currentTeamResponse.setTeamRankNum(rankNum);
                sameScoreCount = 0;
            }
            sameScoreCount++;
            previousScore = currentTeamResponse.getTeamScore();

            teamRepository.save(currentTeam);
        }

        // note : 주간 랭킹 집계와 다르게 팀 랭크 히스토리에 저장하지 않음!

        return rankedTeams;
    }

    public TeamDetailResponse detailTeamInfo(Long teamId) {

        Team team = getTeam(teamId);

        List<TeamMemberExpenseResponse> members = team.getMembers().stream()
                .filter(Objects::nonNull)
                .map(member -> {
                    List<MemberExpensesDetailResponse> lastWeekExpenses = member.getMemberExpensesOfLastWeek().stream()
                            .map(expense -> new MemberExpensesDetailResponse(expense.getExpenseDate(), expense.getExpenseAmount()))
                            .collect(Collectors.toList());
                    List<MemberExpensesDetailResponse> thisWeekExpenses = member.getMemberExpensesOfThisWeek().stream()
                            .map(expense -> new MemberExpensesDetailResponse(expense.getExpenseDate(), expense.getExpenseAmount()))
                            .collect(Collectors.toList());
                    return new TeamMemberExpenseResponse(
                            member.getMemberNickname(),
                            lastWeekExpenses,
                            thisWeekExpenses);
                })
                .collect(Collectors.toList());

        // 그 동안의 랭킹 내역이 없을 때
        if (team.getTeamRankHistories() == null || team.getTeamRankHistories().isEmpty()) {

            return TeamDetailResponse.builder()
                    .teamId(team.getTeamId())
                    .teamName(team.getTeamName())
                    .teamLeaderId(team.getTeamLeaderId())
                    .teamInfo(team.getTeamInfo())
                    .teamScore(team.getTeamScore())
                    // todo : 지난주 랭킹 ? 실시간 랭킹 ?
                    .teamRankNum(0)
                    .members(members)
                    .build();
        } else {
            int lastIndex = team.getTeamRankHistories().size() - 1;

            return TeamDetailResponse.builder()
                    .teamId(team.getTeamId())
                    .teamName(team.getTeamName())
                    .teamLeaderId(team.getTeamLeaderId())
                    .teamInfo(team.getTeamInfo())
                    .teamScore(team.getTeamScore())
                    // todo : 지난주 랭킹 ? 실시간 랭킹 ?
                    .teamRankNum(team.getTeamRankHistories().get(lastIndex).getRankNum())
                    .members(members)
                    .build();
        }
    }

    public List<TeamSearchResponse> searchTeamList(String teamName) {

        List<Team> teams = null;

        if (teamName == null) {
            teams = teamRepository.findAll();
        } else {
            teams = teamRepository.findByTeamNameContaining(teamName);
        }

        List<TeamSearchResponse> result = new ArrayList<>();
        for (Team team : teams) {

            // 팀 리더
            Member member = getMember(team.getTeamLeaderId());

            TeamSearchResponse build = TeamSearchResponse.builder()
                    .teamId(team.getTeamId())
                    .teamName(team.getTeamName())
                    .teamMembersNum(team.getMembers().size())
                    .teamLeaderNickname(member.getMemberNickname())
                    .teamIsAutoConfirm(team.getTeamIsAutoConfirm())
                    .build();

            result.add(build);
        }

        return result;

    }

    @Transactional
    public TeamModifyResponse modifyTeam(Long teamId, TeamModifyRequest request) {

        Team team = getTeam(teamId);

        // 팀장만 가능
        if (team.getTeamLeaderId().equals(request.getMemberId())) {

            team = Team.modifyTeam(team, request);

            teamRepository.save(team);

            TeamModifyResponse response = TeamModifyResponse.builder()
                    .teamName(team.getTeamName())
                    .teamLeaderId(team.getTeamLeaderId())
                    .teamIsAutoConfirm(team.getTeamIsAutoConfirm())
                    .teamInfo(team.getTeamInfo())
                    .build();

            return response;

        } else {
            throw new IllegalArgumentException("팀 정보 수정은 팀장만 가능합니다.");
        }
    }

    @Transactional
    public void leaveTeam(SimpleTeamDto request) {

        Team team = getTeam(request.getTeamId());
        ChatRoom chatRoom = team.getChatRoom();
        Member member = getMember(request.getMemberId());

        if (team.getTeamLeaderId().equals(member.getMemberId())) {
            throw new IllegalArgumentException("팀장은 팀을 탈퇴할 수 없습니다.");
        } else {

            // 팀, 채팅방에서 해당 유저 삭제
            team.getMembers().remove(member);
            chatRoom.getMembers().remove(member);

            teamRepository.save(team);
            chatRoomRepository.save(chatRoom);

            // 유저 정보에서 팀, 채팅방 삭제
            member.setTeam(null);
            member.setMemberChatRoom(null);

            memberRepository.save(member);
        }

    }

    @Transactional
    public void deleteTeam(SimpleTeamDto request) {

        Team team = getTeam(request.getTeamId());
        Member leader = getMember(request.getMemberId());
        ChatRoom chatRoom = team.getChatRoom();
        List<Member> members = team.getMembers();

        if (!team.getTeamLeaderId().equals(leader.getMemberId())) {
            // 팀장이 아니라면 삭제 불가
            throw new IllegalArgumentException("팀 삭제는 팀장만 가능합니다.");
        } else {

            // 멤버 모두 팀, 채팅방 삭제
            for (Member member : members) {
                member.setTeam(null);
                member.setMemberChatRoom(null);
            }

            // 팀, 채팅방 삭제
            teamRepository.delete(team);
            chatRoomRepository.delete(chatRoom);

        }

    }

    @Transactional
    public void banishMember(TeamBanishRequest request) {

        Team team = getTeam(request.getTeamId());
        ChatRoom chatRoom = team.getChatRoom();
        Member leader = getMember(request.getTeamLeaderId());
        Member targetMember = getMember(request.getTargetMemberId());

        // 팀장만 추방 가능
        if (!team.getTeamLeaderId().equals(leader.getMemberId())) {
            throw new IllegalArgumentException("팀원 추방은 팀장만 가능합니다.");
        } else {

            // 팀장 자신은 추방 불가
            if (leader.getMemberId().equals(targetMember.getMemberId())) {
                throw new IllegalArgumentException("팀원만 추방 할 수 있습니다.");
            }

            // 팀 구성원에 해당 유저 있는지 확인
            if (!team.getMembers().contains(targetMember)) {
                throw new IllegalArgumentException("해당 팀원을 찾을 수 없습니다.");
            } else {

                // 유저 팀, 채팅방 삭제
                targetMember.setTeam(null);
                targetMember.setMemberChatRoom(null);

                // 차단 당한 팀에 해당 팀 추가
                targetMember.setMemberBanishedTeam(team);

                memberRepository.save(targetMember);

                // 팀, 채팅방에서 유저 삭제
                team.getMembers().remove(targetMember);
                chatRoom.getMembers().remove(targetMember);

                // 추방한 유저에 추가
                team.getTeamBanishedList().add(targetMember);

                teamRepository.save(team);
                chatRoomRepository.save(chatRoom);

            }

        }

    }


    private int calculateSavingScore(Double lastWeekTotalExpenses, Double thisWeekTotalExpenses) {

        // 이번주 지출이 지난주 지출과 같거나 크다면 절약점수는 0
        if (thisWeekTotalExpenses >= lastWeekTotalExpenses) {
            return 0;
        } else {
            double savingsScore = ((double) (lastWeekTotalExpenses - thisWeekTotalExpenses) / lastWeekTotalExpenses) * 100;
            // 정수로 변환하여 반환
            return (int) savingsScore;
        }
    }

    private int calculateAttendanceScore(Double totalAttendance, Integer memberCount) {
        double savingScore = ((double) totalAttendance / (memberCount * 7)) * 100;
        return (int) savingScore;
    }

    public List<Expense> calculateThisWeekExpenses(List<Expense> allExpenses) {

        List<Expense> expensesOfThisWeek = new ArrayList<>();

        for (Expense expense : allExpenses) {
            LocalDate expenseDate = expense.getExpenseDate();
            // 지난주 일요일 이후 ~ 다음주 월요일 이전 : 이번주 월~일요일
            if (expenseDate.isAfter(SUNDAY_OF_LAST_WEEK) && expenseDate.isBefore(MONDAY_OF_NEXT_WEEK)) {
                expensesOfThisWeek.add(expense);
            }
        }

        return expensesOfThisWeek;
    }

    public List<Expense> calculateLastWeekExpenses(List<Expense> allExpenses) {

        List<Expense> expensesOfLastWeek = new ArrayList<>();

        for (Expense expense : allExpenses) {
            LocalDate expenseDate = expense.getExpenseDate();
            // 지지난주 일요일 이후 ~ 이번주 월요일 이전 : 지난주 월~일요일
            if (expenseDate.isAfter(SUNDAY_OF_TWO_LAST_WEEK) && expenseDate.isBefore(MONDAY_OF_THIS_WEEK)) {
                expensesOfLastWeek.add(expense);
            }
        }

        return expensesOfLastWeek;
    }

    private Team getTeam(Long teamId) {
        return teamRepository.findByTeamId(teamId)
                .orElseThrow(() -> new IllegalArgumentException("팀 정보를 찾을 수 없습니다."));
    }

    private Member getMember(Long memberId) {
        return memberRepository.findByMemberId(memberId);
    }

}
