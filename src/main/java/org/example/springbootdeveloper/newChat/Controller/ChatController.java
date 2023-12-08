package org.example.springbootdeveloper.newChat.Controller;

import org.example.springbootdeveloper.animal_type.domain.UserAnimal;
import org.example.springbootdeveloper.animal_type.repository.UserAnimalRepository;
import org.example.springbootdeveloper.animal_type.service.AnimalService;
import org.example.springbootdeveloper.newChat.domain.ChatMessage;
import org.example.springbootdeveloper.newChat.domain.ChatUser;
import org.example.springbootdeveloper.newChat.dto.ChatMessageDTO;
import org.example.springbootdeveloper.newChat.dto.NickAniDTO;
import org.example.springbootdeveloper.newChat.repository.ChatMessageRepository;
import org.example.springbootdeveloper.newChat.repository.ChatUserRepository;
import org.example.springbootdeveloper.newChat.service.WebSocketChat;
import org.example.springbootdeveloper.user.domain.User;
import org.example.springbootdeveloper.user.respository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ChatController {
    private final UserAnimalRepository userAnimalRepository;

    private final AnimalService animalService;
    private final ChatUserRepository chatUserRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;

    private static Logger logger = LoggerFactory.getLogger(ChatController.class);

    public ChatController(UserAnimalRepository userAnimalRepository, AnimalService animalService, ChatUserRepository chatUserRepository, ChatMessageRepository chatMessageRepository, UserRepository userRepository) {
        this.userAnimalRepository = userAnimalRepository;
        this.animalService = animalService;
        this.chatUserRepository = chatUserRepository;
        this.chatMessageRepository = chatMessageRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/chat/{otherNickname}")
    public ResponseEntity<String> newChat(@PathVariable String otherNickname, Principal principal) {
        String combinedString = otherNickname + principal.getName();
        String chatRoomId = otherNickname;
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
                    .chatRoomId(chatMessageDTO.getChatRoomId())
                    .message(chatMessageDTO.getMessage())
                   .date(chatMessageDTO.getDate())
                   .nickname(chatMessageDTO.getNickname())
                   .build());
    }



    //json 형식으로 저장된 메시지들 모두 주는 로직도 생성하자.
    @GetMapping("/messages/{chatRoomId}")
    public ResponseEntity<List<ChatMessageDTO>> getMessagesByChatRoomId(@PathVariable String chatRoomId) {
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

    @GetMapping("/chat/{chatRoomId}/another")
    public ResponseEntity<NickAniDTO> chatAnother(@PathVariable String chatRoomId, Principal principal) {
        String myUserId = principal.getName();
        String otherUserName;
        String myNickName;
        Optional<User> userOptional1 = userRepository.findByUserId(myUserId);
        if (userOptional1.isPresent()) {
            User user1 = userOptional1.get();
            myNickName = user1.getNickname();
        }
        else {
            myNickName = "";
        }



        // 1. 해당 채팅 방의 모든 사용자 가져오기
        Optional<List<ChatUser>> optionalChatUsers = chatUserRepository.findByChatRoomId(chatRoomId);

        if (optionalChatUsers.isPresent()) {
            List<ChatUser> chatUsers = optionalChatUsers.get();

            // 2. 현재 사용자를 제외한 다른 사용자 찾기
            Optional<ChatUser> otherUserOptional = chatUsers.stream()
                    .filter(user -> !user.getChatUserId().equals(myNickName))
                    .findFirst();

            if (otherUserOptional.isPresent()) {
                ChatUser otherUser = otherUserOptional.get();
                otherUserName = otherUser.getChatUserId();
                Optional<User> userOptional = userRepository.findByNickname(otherUserName);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    String userId = user.getUserId();
                    Optional<UserAnimal> userAnimalOptional = userAnimalRepository.findByUserId(userId);
                    UserAnimal userAnimal = userAnimalOptional.get();
                    String animal = userAnimal.getAnimal();
                    if ("polar bear".equals(animal)) {
                        animal = "polarBear";
                    } else if ("arctic fox".equals(animal)) {
                        animal = "arcticFox";
                    }
                    NickAniDTO nickAniDTO = NickAniDTO.builder()
                            .animal(animal)
                            .nickname(otherUserName)
                            .build();


                    return new ResponseEntity<>(nickAniDTO, HttpStatus.OK);
                }



            }
        }

        // 처리할 수 있는 상황이 아닌 경우
        return ResponseEntity.notFound().build();
    }




}
