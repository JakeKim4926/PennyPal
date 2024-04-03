package com.ssafy.pennypal.domain.card.dto.request;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchCard {

    private String cardName;

    private String cardCompany;

    private Integer startCardRequired;

    private Integer endCardRequired;

    private Integer startCardDomestic;

    private Integer endCardDomestic;

    @Builder
    public SearchCard(String cardName, String cardCompany, Integer startCardRequired, Integer endCardRequired, Integer startCardDomestic, Integer endCardDomestic) {
        this.cardName = cardName;
        this.cardCompany = cardCompany;
        this.startCardRequired = startCardRequired;
        this.endCardRequired = endCardRequired;
        this.startCardDomestic = startCardDomestic;
        this.endCardDomestic = endCardDomestic;
    }
}
