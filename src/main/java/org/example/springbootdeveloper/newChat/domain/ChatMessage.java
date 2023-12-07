package org.example.springbootdeveloper.newChat.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name= "chat_messages")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "chat_room_id", nullable = false)
    private Integer chatRoomId;

    @Column(name = "sender_id", nullable = false)
    private String sender;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "dates", nullable = false)
    private String date;


    @Builder
    public ChatMessage(Integer chatRoomId, String sender, String message, String date)
    {
        this.message = message;
        this.sender = sender;
        this.date = date;
        this.chatRoomId = chatRoomId;
    }


}
