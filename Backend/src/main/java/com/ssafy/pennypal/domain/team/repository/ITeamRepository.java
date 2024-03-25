package com.ssafy.pennypal.domain.team.repository;

import com.ssafy.pennypal.domain.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITeamRepository extends JpaRepository<Team, Long> {

    Optional<Team> findByTeamName(String teamName);

    Optional<Team> findByTeamId(Long teamId);

    List<Team> findByTeamNameContaining(String teamName);


}
