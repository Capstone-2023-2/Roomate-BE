package org.example.springbootdeveloper.newChat.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ChatMessageDTO {

    private Integer chatRoomId;

    private String senderId;

    private String message;

    private String date;

    @Builder
    public ChatMessageDTO(Integer chatRoomId, String senderId, String message, String date)
    {
        this.chatRoomId = chatRoomId;
        this.senderId = senderId;
        this.message =message;
        this.date = date;
    }

}
