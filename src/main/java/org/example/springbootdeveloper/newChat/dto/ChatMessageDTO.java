package org.example.springbootdeveloper.newChat.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ChatMessageDTO {

    private String chatRoomId;

    private String nickname;

    private String message;

    private String date;

    @Builder
    public ChatMessageDTO(String chatRoomId, String nickname, String message, String date)
    {
        this.chatRoomId = chatRoomId;
        this.nickname = nickname;
        this.message =message;
        this.date = date;
    }

}
