package com.ssafy.pennypal.domain.team.repository;

import com.ssafy.pennypal.domain.team.entity.TeamRankHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITeamRankHistoryRepository extends JpaRepository <TeamRankHistory, Long> {
}
