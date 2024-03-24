package com.ssafy.pennypal.domain.team.entity;

import com.ssafy.pennypal.domain.member.entity.Member;
import com.ssafy.pennypal.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long teamId;                                        // 팀 Id

    @Column(name = "team_name")
    private String teamName;                                    // 팀 이름

    @Setter
    @Column(name = "team_score")
    private Integer teamScore;                                  // 팀 점수

    @Column(
            name = "team_is_auto_confirm",
            columnDefinition = "TINYINT(1)"
    )
    private Boolean teamIsAutoConfirm;                          // 자동 가입 승인 여부 (true = 자동 / false = 수동)

    @Column(name = "team_leader_id")
    private Long teamLeaderId;                                  // 팀장 Id

    @OneToMany(
            mappedBy = "team",
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<Member> members = new ArrayList<>();          // 팀 구성원

    @Column(name = "team_info")
    private String teamInfo;                                   // 팀 한줄소개

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "memberWaitingTeam",
            cascade = CascadeType.ALL
    )
    @Column(name = "team_waiting_list")
    private List<Member> TeamWaitingList = new ArrayList<>();  // 가입 승인 대기 리스트

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "team",
            cascade = CascadeType.ALL
    )
    @Setter
    @Column(name = "team_rank_histories")
    private List<TeamRankHistory> TeamRankHistories = new ArrayList<>();               // 랭킹 내역


    @Builder
    private Team(String teamName, Boolean teamIsAutoConfirm, Long teamLeaderId, String teamInfo, Member member) {
        this.teamName = teamName;
        this.teamScore = 0;
        this.teamIsAutoConfirm = teamIsAutoConfirm;
        this.teamLeaderId = teamLeaderId;
        this.members.add(member);
        this.teamInfo = teamInfo;
    }

}

