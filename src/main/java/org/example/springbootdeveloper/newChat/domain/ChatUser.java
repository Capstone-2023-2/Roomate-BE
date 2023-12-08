package org.example.springbootdeveloper.newChat.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "chat_users")
public class ChatUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "chat_room_id", nullable = false)
    private String chatRoomId;

    @Column(name = "chat_user_id", nullable = false)
    private String chatUserId;

    @Builder
    public ChatUser(String chatRoomId, String chatUserId) {
        this.chatRoomId = chatRoomId;
        this.chatUserId = chatUserId;
    }
}
