package com.ssafy.pennypal.domain.member.entity;

import com.querydsl.core.annotations.QueryProjection;
import com.ssafy.pennypal.domain.chat.entity.ChatMessage;
import com.ssafy.pennypal.domain.chat.entity.ChatRoom;
import com.ssafy.pennypal.domain.market.entity.Order;
import com.ssafy.pennypal.domain.team.entity.Team;
import com.ssafy.pennypal.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;                                                      // 사용자 Id

    @Column(name = "member_email")
    private String memberEmail;                                                 // 사용자 이메일

    @Column(name = "member_password")
    private String memberPassword;                                              // 사용자 비밀번호

    @Column(name = "member_name")
    private String memberName;                                                  // 사용자 이름

    @Column(name = "member_nickname")
    private String memberNickname;                                              // 사용자 닉네임

    @Column(name = "member_birth_date")
    private LocalDateTime memberBirthDate;                                      // 사용자 생일 YYYY-MM-DD (1995-12-06)

    // 사용자 보상 포인트
    @Setter
    @Column(name = "member_point")
    private Integer memberPoint;                                                // 사용자 포인트

    @Column(name = "member_most_category")
    private String memberMostCategory;                                          // 사용자 최대 지출 카테고리

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REMOVE}
    )
    @JoinColumn(name = "team_id")
    @Setter
    private Team team;                                                           // 사용자가 참여한 팀

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "member"
    )
    private List<Order> orders = new ArrayList<>();                              // 주문목록 조회
    // 나중에 또 수정

    //0319 김민건 수정 사용자 계정 생성
    @Column(name = "member_bank_api")
    @Setter
    private String memberBankApi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_waiting_list")
    @Setter
    private Team memberWaitingTeam;                                               // 사용자가 가입 요청한 팀

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "member")
    @Column(name = "member_expenses_of_last_week")
    private List<Expense> memberExpensesOfLastWeek = new ArrayList<>();           // 지난 주 지출 내역

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "member")
    @Column(name = "member_expenses_of_this_week")
    private List<Expense> memberExpensesOfThisWeek = new ArrayList<>();            // 이번 주 지출 내역

    @Column(name = "member_attendance")
    private Integer memberAttendance;                                              // 이번 주 출석 횟수

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    @Setter
    private ChatRoom memberChatRoom;                                                     // 참여 중인 채팅방

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "member"
    )
    @Column(name = "chat_message")
    private List<ChatMessage> memberChatMessage;                                          // 보낸 메시지

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_banished_list")
    @Setter
    private Team memberBanishedTeam;                                                // 가입 차단 당한 팀


    @Builder
    @QueryProjection
    public Member(
            String memberEmail, String memberPassword, String memberName, String memberNickname,
            LocalDateTime memberBirthDate, Integer memberPoint, String memberMostCategory, Team team, List<Order> orders,
            String memberBankApi, Team memberWaitingTeam, Integer memberAttendance
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
        this.memberAttendance = memberAttendance;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return memberPassword;
    }

    @Override
    public String getUsername() {
        return memberEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}