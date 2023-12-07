package org.example.springbootdeveloper.newChat.repository;

import org.example.springbootdeveloper.newChat.domain.ChatMessage;
import org.example.springbootdeveloper.recommend.domain.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    Optional<ChatMessage> findByChatRoomId(Integer chatRoomId);
}
