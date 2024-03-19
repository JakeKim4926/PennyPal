package com.ssafy.pennypal.domain.member.repository;

import com.ssafy.pennypal.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMemberRepository extends JpaRepository<Member, Long> {
    Member findByMemberEmail(String memberEmail);
}
