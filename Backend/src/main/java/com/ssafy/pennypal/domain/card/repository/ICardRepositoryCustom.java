package com.ssafy.pennypal.domain.card.repository;

import com.ssafy.pennypal.domain.card.dto.request.SearchCard;
import com.ssafy.pennypal.domain.card.entity.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICardRepositoryCustom {

    Page<Card> findCardsWithCustomQuery(Pageable pageable, SearchCard searchCard);
}
