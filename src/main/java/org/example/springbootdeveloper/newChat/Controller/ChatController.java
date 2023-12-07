package org.example.springbootdeveloper.newChat.Controller;

import org.example.springbootdeveloper.newChat.domain.ChatMessage;
import org.example.springbootdeveloper.newChat.domain.ChatUser;
import org.example.springbootdeveloper.newChat.dto.ChatMessageDTO;
import org.example.springbootdeveloper.newChat.repository.ChatMessageRepository;
import org.example.springbootdeveloper.newChat.repository.ChatUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class ChatController {
    private final ChatUserRepository chatUserRepository;
    private final ChatMessageRepository chatMessageRepository;

    public ChatController(ChatUserRepository chatUserRepository, ChatMessageRepository chatMessageRepository) {
        this.chatUserRepository = chatUserRepository;
        this.chatMessageRepository = chatMessageRepository;
    }

    @PostMapping("/chat/{otherNickname}")
    public ResponseEntity<Integer> newChat(@PathVariable String otherNickname, Principal principal) {
        String combinedString = otherNickname + principal.getName();
        int chatRoomId = combinedString.hashCode();

        chatUserRepository.save(ChatUser.builder()
                .chatUserId(otherNickname)
                .chatRoomId(chatRoomId)
                .build());
        chatUserRepository.save(ChatUser.builder()
                .chatUserId(principal.getName())
                .chatRoomId(chatRoomId)
                .build());

        return ResponseEntity.ok(chatRoomId);
    }

    //json 형식으로 메시지 주고 그거 저장하는 로직 생성하자
    @PostMapping("/sending/message")
    public void sendMessage(@RequestBody ChatMessageDTO chatMessageDTO) {
        // 받은 JSON 데이터를 ChatMessage 엔티티로 변환
        chatMessageRepository.save( ChatMessage.builder()
                    .chatRoomId(chatMessageDTO.getChatRoomId())
                    .message(chatMessageDTO.getMessage())
                   .date(chatMessageDTO.getDate())
                   .senderId(chatMessageDTO.getSenderId())
                   .build());
    }





    //json 형식으로 저장된 메시지들 모두 주는 로직도 생성하자.



}
