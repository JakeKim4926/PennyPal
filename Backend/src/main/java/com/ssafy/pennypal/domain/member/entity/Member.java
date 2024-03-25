package com.ssafy.pennypal.domain.member.entity;

import com.querydsl.core.annotations.QueryProjection;
import com.ssafy.pennypal.domain.market.entity.Order;
import com.ssafy.pennypal.domain.team.entity.Team;
import com.ssafy.pennypal.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;                                      // 사용자 Id

    @Column(name = "member_email")
    private String memberEmail;                                 // 사용자 이메일

    @Column(name = "member_password")
    private String memberPassword;                              // 사용자 비밀번호

    @Column(name = "member_name")
    private String memberName;                                  // 사용자 이름

    @Column(name = "member_nickname")
    private String memberNickname;                              // 사용자 닉네임

    @Column(name = "member_birth_date")
    private LocalDateTime memberBirthDate;                      // 사용자 생일 YYYY-MM-DD (1995-12-06)

    // 사용자 보상 포인트
    @Setter
    @Column(name = "member_point")
    private Integer memberPoint;                                // 사용자 포인트

    @Column(name = "member_most_category")
    private String memberMostCategory;                          // 사용자 최대 지출 카테고리

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REMOVE}
    )
    @JoinColumn(name = "team_id")
    private Team team;                                          // 사용자가 참여한 팀

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "member"
    )
    private List<Order> orders = new ArrayList<>();              // 주문목록 조회
    // 나중에 또 수정

    //0319 김민건 수정 사용자 계정 생성
    @Column(name = "member_bank_api")
    private String memberBankApi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_waiting_list")
    private Team memberWaitingTeam;                                   // 사용자가 가입 요청한 팀

    @Column(name = "member_last_week_expenses")
    private Double memberLastWeekExpenses;                           // 지난 주 지출 총액

    @Column(name = "member_this_week_expenses")
    private Double memberThisWeekExpenses;                            // 이번 주 지출 총액

    @Column(name = "member_attendance")
    private Double memberAttendance;                                 // 이번 주 출석 횟수

    @Builder
    @QueryProjection
    public Member(
            String memberEmail, String memberPassword, String memberName, String memberNickname,
            LocalDateTime memberBirthDate, Integer memberPoint, String memberMostCategory, Team team,
            List<Order> orders, String memberBankApi, Team memberWaitingTeam, Double memberLastWeekExpenses,
            Double memberThisWeekExpenses, Double memberAttendance
    ) {
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.memberNickname = memberNickname;
        this.memberBirthDate = memberBirthDate;
        this.memberPoint = memberPoint;
        this.memberMostCategory = memberMostCategory;
        this.team = team;
        this.orders = orders;
        this.memberBankApi = memberBankApi;
        this.memberWaitingTeam = memberWaitingTeam;
        this.memberLastWeekExpenses = memberLastWeekExpenses;
        this.memberThisWeekExpenses = memberThisWeekExpenses;
        this.memberAttendance = memberAttendance;
    }


    public void setMemberBankApi(String memberBankApi) {
        this.memberBankApi = memberBankApi;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setMemberWaitingTeam(Team waitingTeam) {
        this.memberWaitingTeam = waitingTeam;
    }
}
