package com.ssafy.pennypal.domain.team.repository;

import com.ssafy.pennypal.domain.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITeamRepository extends JpaRepository<Team, Long> {

    Team findByTeamName(String teamName);

    Team findByTeamId(Long teamId);

    List<Team> findByTeamNameContaining(String teamName);


}
