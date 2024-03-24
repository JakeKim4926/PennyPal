package com.ssafy.pennypal.domain.chat.repository;

import com.ssafy.pennypal.domain.chat.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}
