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

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chat_room_id_seq")
    @SequenceGenerator(name = "chat_room_id_seq", sequenceName = "chat_room_id_seq", allocationSize = 1)
    @Column(name = "chat_room_id", nullable = false, unique = true)
    private Integer chatRoomId;

    @Column(name = "chat_user_id", nullable = false)
    private String chatUserId;

    @Builder
    public ChatUser(Integer chatRoomId, String chatUserId) {
        this.chatRoomId = chatRoomId;
        this.chatUserId = chatUserId;
    }
}
