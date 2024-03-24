package com.ssafy.pennypal.domain.chat.repository;

import com.ssafy.pennypal.domain.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    ChatRoom findByChatRoomId(Long roomId);
}
