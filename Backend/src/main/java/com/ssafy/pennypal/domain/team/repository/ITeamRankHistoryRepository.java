package com.ssafy.pennypal.domain.team.repository;

import com.ssafy.pennypal.domain.team.entity.TeamRankHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ITeamRankHistoryRepository extends JpaRepository <TeamRankHistory, Long> {

    List<TeamRankHistory> findByRankDate(LocalDate rankDate);
}
