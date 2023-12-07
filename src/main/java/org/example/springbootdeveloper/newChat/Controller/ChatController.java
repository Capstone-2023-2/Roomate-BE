package org.example.springbootdeveloper.newChat.Controller;

import org.example.springbootdeveloper.newChat.domain.ChatUser;
import org.example.springbootdeveloper.newChat.repository.ChatUserRepository;
import org.example.springbootdeveloper.user.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
public class ChatController {
    private final ChatUserRepository chatUserRepository;

    public ChatController(ChatUserRepository chatUserRepository) {
        this.chatUserRepository = chatUserRepository;
    }

    @PostMapping("/chat/{otherNickname}")
    public ResponseEntity<String> newChat(@PathVariable String otherNickname, Principal principal) {
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
        return ResponseEntity.ok("채팅 생성 성공");
    }


}
