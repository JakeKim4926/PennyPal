package com.example.demo.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long cardId;                                        // 카드 Id

    @Column(name = "card_name", insertable=false, updatable=false)
    private String cardName;                                    // 카드명

    // 체크카드 or 신용카드
    @Column(name = "card_is_check")
    private Boolean isCheck;                                    // 체크카드/신용카드 여부

    @Column(name = "card_name")
    private String cardCompany;                                 // 카드 회사명

    // 국내 연회비
    @Column(name = "card_domestic")
    private Integer cardDomestic;                               // 국내 연회비

    // 해외 연회비
    @Column(name = "card_abroad")
    private Integer cardAbroad;                                 // 해외 연회비

    @Column(name = "card_category")
    private String cardCategory;                                // 카드 카테고리 ( 추천용 )
                                                                // 혜택에서 직접 추출(?)

    private List<String> cardBenefits = new ArrayList<>();      // 카드 혜택
}
