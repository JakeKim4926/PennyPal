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
    @JoinColumn(name = "team_rank_histories")
    private Team team;

    @Column(name = "rank_date")
    private LocalDate rankDate;

    @Column(name = "rank_num")
    private Integer rankNum;

    @Builder
    private TeamRankHistory(Team team, LocalDate rankDate, Integer rankNum) {
        this.team = team;
        this.rankDate = rankDate;
        this.rankNum = rankNum;
    }
}
