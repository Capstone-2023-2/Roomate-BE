package org.example.springbootdeveloper.newChat.Controller;

import org.example.springbootdeveloper.animal_type.domain.UserAnimal;
import org.example.springbootdeveloper.newChat.domain.ChatMessage;
import org.example.springbootdeveloper.newChat.domain.ChatUser;
import org.example.springbootdeveloper.newChat.dto.ChatMessageDTO;
import org.example.springbootdeveloper.newChat.repository.ChatMessageRepository;
import org.example.springbootdeveloper.newChat.repository.ChatUserRepository;
import org.example.springbootdeveloper.user.domain.User;
import org.example.springbootdeveloper.user.respository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ChatController {
    private final ChatUserRepository chatUserRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;

    public ChatController(ChatUserRepository chatUserRepository, ChatMessageRepository chatMessageRepository, UserRepository userRepository) {
        this.chatUserRepository = chatUserRepository;
        this.chatMessageRepository = chatMessageRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/chat/{otherNickname}")
    public ResponseEntity<String> newChat(@PathVariable String otherNickname, Principal principal) {
        String combinedString = otherNickname + principal.getName();
        Integer chatRoomId = combinedString.hashCode();
        Optional<User> userOptional = userRepository.findByUserId(principal.getName());
        if(userOptional.isPresent()){
            User user = userOptional.get();
            String usernickname = user.getNickname();
            chatUserRepository.save(ChatUser.builder()
                    .chatUserId(otherNickname)
                    .chatRoomId(chatRoomId)
                    .build());
            chatUserRepository.save(ChatUser.builder()
                    .chatUserId(usernickname)
                    .chatRoomId(chatRoomId)
                    .build());
        }

        return ResponseEntity.ok(String.valueOf(chatRoomId));
    }

    //json 형식으로 메시지 주고 그거 저장하는 로직 생성하자
    @PostMapping("/sending/message")
    public void sendMessage(@RequestBody ChatMessageDTO chatMessageDTO) {

        // 받은 JSON 데이터를 ChatMessage 엔티티로 변환
        chatMessageRepository.save( ChatMessage.builder()
                    .chatRoomId(Integer.parseInt(chatMessageDTO.getChatRoomId()))
                    .message(chatMessageDTO.getMessage())
                   .date(chatMessageDTO.getDate())
                   .nickname(chatMessageDTO.getNickname())
                   .build());
    }



    //json 형식으로 저장된 메시지들 모두 주는 로직도 생성하자.
    @GetMapping("/messages/{chatRoomId}")
    public ResponseEntity<List<ChatMessageDTO>> getMessagesByChatRoomId(@PathVariable Integer chatRoomId) {
        Optional<List<ChatMessage>> chatMessages = chatMessageRepository.findByChatRoomId(chatRoomId);

        if (chatMessages.isPresent()) {
            List<ChatMessageDTO> chatMessageDTOs = new ArrayList<>();

            for (ChatMessage chatMessage : chatMessages.get()) {
                // Convert ChatMessage entity to ChatMessageDTO if needed
                ChatMessageDTO chatMessageDTO = new ChatMessageDTO();
                chatMessageDTO.setChatRoomId(String.valueOf(chatMessage.getChatRoomId()));
                chatMessageDTO.setMessage(chatMessage.getMessage());
                chatMessageDTO.setDate(chatMessage.getDate());
                chatMessageDTO.setNickname(chatMessage.getNickname());

                chatMessageDTOs.add(chatMessageDTO);
            }

            return new ResponseEntity<>(chatMessageDTOs, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




}
