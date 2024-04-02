package com.ssafy.pennypal.domain.card.repository;

import com.ssafy.pennypal.domain.card.entity.Card;
import com.ssafy.pennypal.domain.member.entity.Attend;
import com.ssafy.pennypal.domain.team.entity.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICardRepository extends JpaRepository<Card, Long> {
    Page<Card> findAll(Pageable pageable);
}
