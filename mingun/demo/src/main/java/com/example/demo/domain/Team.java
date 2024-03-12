package com.example.demo.domain;

import com.example.demo.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @Column(name = "team_people_number")
    private Integer teamPeopleNumber;                           // 팀 인원

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
    private List<Member> members;                              // 팀 구성원
}

