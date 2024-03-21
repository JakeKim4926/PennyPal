package com.ssafy.pennypal.domain.team.entity;

import com.ssafy.pennypal.domain.member.entity.Member;
import com.ssafy.pennypal.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Team extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long teamId;                                        // 팀 Id

    @Column(name = "team_name")
    private String teamName;                                    // 팀 이름

    @Column(name = "team_score")
    private Integer teamScore;                                  // 팀 점수

    @Column(name = "team_is_auto_confirm")
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

    @OneToMany(mappedBy = "waitingTeam", cascade = CascadeType.ALL)
    @Column(name = "waiting_list")
    private List<Member> waitingList = new ArrayList<>();      // 가입 승인 대기 리스트

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

