package com.ssafy.pennypal.domain.card.repository;

import com.ssafy.pennypal.domain.card.entity.Card;
import com.ssafy.pennypal.domain.member.entity.Attend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICardRepository extends JpaRepository<Card, Long> {
}
