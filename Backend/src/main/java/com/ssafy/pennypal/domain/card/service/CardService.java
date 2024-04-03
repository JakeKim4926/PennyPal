package com.ssafy.pennypal.domain.card.service;

import com.ssafy.pennypal.domain.card.dto.request.SearchCard;
import com.ssafy.pennypal.domain.card.dto.response.CardResponse;
import com.ssafy.pennypal.domain.card.entity.Card;
import com.ssafy.pennypal.domain.card.repository.ICardRepository;
import com.ssafy.pennypal.global.common.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CardService {
    private final ICardRepository repository;

    public ApiResponse<Page<CardResponse>> getCards(Pageable pageable, SearchCard searchCard) {

        log.info("searchCard = {}", searchCard);
        Page<Card> cardPage = repository.findCardsWithCustomQuery(pageable, searchCard);
        Page<CardResponse> cardResponses = cardPage.map(this::convertToResponse);

        if (cardResponses.isEmpty())
            ApiResponse.of(HttpStatus.NO_CONTENT, "데이터가 존재하지 않습니다.");

        return ApiResponse.ok(cardResponses);
    }

    private CardResponse convertToResponse(Card card) {
        return CardResponse.builder()
                .cardId(card.getCardId())
                .cardType(card.getCardType())
                .cardCompany(card.getCardCompany())
                .cardName(card.getCardName())
                .cardBenefitType(card.getCardBenefitType())
                .cardImg(card.getCardImg())
                .cardTopCategory(card.getCardTopCategory())
                .cardCategory(card.getCardCategory())
                .cardRequired(card.getCardRequired())
                .cardDomestic(card.getCardDomestic())
                .cardAbroad(card.getCardAbroad())
                .build();
    }
}
