package com.ssafy.pennypal.domain.team.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamRankHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @Column(name = "rank_date")
    private LocalDate rankDate;

    @Column(name = "rank_num")
    private Integer rankNum;

    @Column(name = "reward_point")
    private Integer rewardPoint;

    @Builder
    private TeamRankHistory(Team team, LocalDate rankDate, Integer rankNum, Integer rewardPoint) {
        this.team = team;
        this.rankDate = rankDate;
        this.rankNum = rankNum;
        this.rewardPoint = rewardPoint;
    }
}
