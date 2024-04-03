package com.ssafy.pennypal.domain.card.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.ssafy.pennypal.domain.card.dto.request.SearchCard;
import com.ssafy.pennypal.domain.card.entity.Card;
import com.ssafy.pennypal.global.common.support.Querydsl5RepositorySupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.ssafy.pennypal.domain.card.entity.QCard.card;
import static io.micrometer.common.util.StringUtils.isEmpty;

@Repository

public class ICardRepositoryImpl extends Querydsl5RepositorySupport implements ICardRepositoryCustom {

    public ICardRepositoryImpl() {
        super(Card.class);
    }

    public Page<Card> findCardsWithCustomQuery(Pageable pageable, SearchCard searchCard) {

        return applyPagination(pageable, query -> query
                .selectFrom(card)
                .where(
                        cardNameEq(searchCard.getCardName()),
                        cardCompanyEq(searchCard.getCardCompany()),
                        cardRequiredEq(searchCard.getStartCardRequired(), searchCard.getEndCardRequired()),
                        cardDomesticEq(searchCard.getStartCardDomestic(), searchCard.getStartCardDomestic())
                ));
    }

    private BooleanExpression cardNameEq(String cardName) {
        return isEmpty(cardName) ? null : card.cardName.contains(cardName);
    }

    private BooleanExpression cardCompanyEq(String cardCompany) {
        return isEmpty(cardCompany) ? null : card.cardCompany.contains(cardCompany);
    }

    private BooleanExpression cardRequiredEq(Integer startCardRequired, Integer endCardRequired) {
        return Optional.ofNullable(startCardRequired)
                .map(price -> Optional.ofNullable(endCardRequired)
                        .map(endPriceNotNull -> card.cardRequired.between(startCardRequired, endPriceNotNull))
                        .orElseGet(() -> card.cardRequired.goe(price)))
                .orElseGet(() -> Optional.ofNullable(endCardRequired)
                        .map(card.cardRequired::loe)
                        .orElse(null));
    }

    private BooleanExpression cardDomesticEq(Integer startCardDomestic, Integer endCardDomestic) {
        return Optional.ofNullable(startCardDomestic)
                .map(price -> Optional.ofNullable(endCardDomestic)
                        .map(endPriceNotNull -> card.cardDomestic.between(startCardDomestic, endPriceNotNull))
                        .orElseGet(() -> card.cardDomestic.goe(price)))
                .orElseGet(() -> Optional.ofNullable(endCardDomestic)
                        .map(card.cardDomestic::loe)
                        .orElse(null));
    }
}
