package org.example.springbootdeveloper.newChat.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ChatMessageDTO {

    private String chatRoomId;

    private String senderNickname;

    private String message;

    private String date;

    @Builder
    public ChatMessageDTO(String chatRoomId, String senderNickname, String message, String date)
    {
        this.chatRoomId = chatRoomId;
        this.senderNickname = senderNickname;
        this.message =message;
        this.date = date;
    }

}
