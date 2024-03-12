package com.example.demo.domain;

import com.example.demo.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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
    @Column(name = "member_point")
    private Integer memberPoint;                                // 사용자 포인트

    @Column(name = "member_most_category")
    private String memberMostCategory;                          // 사용자 최대 지출 카테고리

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;                                          // 사용자가 참여한 팀


//
//    @OneToMany(
//            fetch = FetchType.LAZY,
//            cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REMOVE},
//            mappedBy = "orderInfo"
//    )
//    private List<OrderInfo> orderInfos = new ArrayList<>();     // 주문목록 조회
    // 나중에 또 수정
}
