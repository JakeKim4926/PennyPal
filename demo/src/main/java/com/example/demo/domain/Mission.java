package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long missionId;                                 // 미션 Id

    @Column(name = "mission_period")
    private Integer missionPeriod;                          // 미션 기간

    @Column(name = "mission_name")
    private String missionName;                             // 미션 이름

    @Column(name = "mission_reward")
    private Integer missionReward;                          // 미션 보상

}
