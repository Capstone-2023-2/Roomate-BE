package org.example.springbootdeveloper.chat.dto;


import lombok.AllArgsConstructor;
import lombok.Data;


@Data

public class ChatMessageDTO {
    private Integer channelId;
    private Integer writerId;
    private String chat;
}