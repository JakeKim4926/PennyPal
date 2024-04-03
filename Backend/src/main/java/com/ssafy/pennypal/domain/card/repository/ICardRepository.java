package com.ssafy.pennypal.domain.card.repository;

import com.ssafy.pennypal.domain.card.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ICardRepository extends JpaRepository<Card, Long>, QuerydslPredicateExecutor<Card>, ICardRepositoryCustom {

}
