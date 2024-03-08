package com.example.demo.domain;

import com.example.demo.common.BaseEntity;
import com.example.demo.enumType.MemberRole;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String memberEmail;

    private String memberPassword;

    private String memberName;

    private String memberNickname;

    private LocalDateTime memberBirthDate;

    private Integer memberPoint;

    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamId")
    private Team team;


    // 주문목록 조회
    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "orderInfo"
    )
    private List<OrderInfo> orderInfos;
    // 나중에 또 수정
}
