package com.ssafy.pennypal.domain.card.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CardResponse {
    private Long cardId;                                        // 카드 Id
    private String cardType;                                    // 체크카드/신용카드 여부
    private String cardCompany;                                 // 카드 회사명
    private String cardName;                                    // 카드명
    private String cardBenefitType;                             // 카드 보상 타입
    private String  cardImg;                                    // 카드 이미지 url
    private String  cardTopCategory;                            // 카드 혜택 top3
    private String  cardCategory;                               // 카드 혜택
    private Integer cardRequired;                               // 전월 실적
    private Integer cardDomestic;                               // 국내 연회비
    private Integer cardAbroad;                                 // 해외 연회비
}
