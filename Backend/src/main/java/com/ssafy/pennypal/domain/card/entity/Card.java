package com.ssafy.pennypal.domain.card.entity;


import com.querydsl.core.annotations.QueryProjection;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long cardId;                                        // 카드 Id

    // 체크카드 or 신용카드
    @Column(name = "card_type")
    private String cardType;                                    // 체크카드/신용카드 여부

    @Column(name = "card_company")
    private String cardCompany;                                 // 카드 회사명

    @Column(name = "card_name", insertable = false, updatable = false)
    private String cardName;                                    // 카드명

    @Column(name = "card_benefit_type")
    private String cardBenefitType;                             // 카드 보상 타입

    @Column(name = "card_img")
    private String cardImg;                                    // 카드 이미지 url

    @Column(name = "card_top_category")
    private String cardTopCategory;                            // 카드 혜택 top3

    @Column(name = "card_category")
    private String cardCategory;                               // 카드 혜택

    @Column(name = "card_required")
    private Integer cardRequired;                               // 전월 실적

    @Column(name = "card_domestic")
    private Integer cardDomestic;                               // 국내 연회비

    @Column(name = "card_abroad")
    private Integer cardAbroad;                                 // 해외 연회비

    // 카드와 카테고리는 1:1 관계
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", unique = true)
    private Category category;                                  // 카드의 카테고리

    private List<String> cardTopBenefits = new ArrayList<>();   // 카드 top3 혜택
    private List<String> cardBenefits = new ArrayList<>();      // 카드 혜택

    @Builder
    @QueryProjection
    public Card(String cardType, String cardCompany, String cardName, String cardBenefitType, String cardImg, String cardTopCategory, String cardCategory, Integer cardRequired, Integer cardDomestic, Integer cardAbroad, Category category, List<String> cardTopBenefits, List<String> cardBenefits) {
        this.cardType = cardType;
        this.cardCompany = cardCompany;
        this.cardName = cardName;
        this.cardBenefitType = cardBenefitType;
        this.cardImg = cardImg;
        this.cardTopCategory = cardTopCategory;
        this.cardCategory = cardCategory;
        this.cardRequired = cardRequired;
        this.cardDomestic = cardDomestic;
        this.cardAbroad = cardAbroad;
        this.category = category;
        this.cardTopBenefits = cardTopBenefits;
        this.cardBenefits = cardBenefits;
    }
}
