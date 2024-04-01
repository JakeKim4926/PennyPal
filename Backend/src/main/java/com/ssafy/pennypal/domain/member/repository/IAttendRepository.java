package com.ssafy.pennypal.domain.member.repository;

import com.ssafy.pennypal.domain.member.entity.Attend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAttendRepository extends JpaRepository<Attend, Long> {
    Attend findByAttendId(Long attendId);
}
