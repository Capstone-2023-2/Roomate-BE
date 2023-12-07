package org.example.springbootdeveloper.newChat.repository;

import org.example.springbootdeveloper.newChat.domain.ChatMessage;
import org.example.springbootdeveloper.newChat.domain.ChatUser;
import org.example.springbootdeveloper.recommend.domain.UserDetail;
import org.example.springbootdeveloper.recommend.domain.UserStar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChatUserRepository extends JpaRepository<ChatUser, Long> {

    Optional<List<ChatUser>> findByChatRoomId(Integer chatRoomId);

    @Query("SELECT cu FROM ChatUser cu WHERE cu.chatRoomId = :chatRoomId AND cu.chatUserId <> :currentUserId")
    Optional<ChatUser> findOtherUserInChatRoom(@Param("chatRoomId") Integer chatRoomId, @Param("currentUserId") String currentUserId);

}