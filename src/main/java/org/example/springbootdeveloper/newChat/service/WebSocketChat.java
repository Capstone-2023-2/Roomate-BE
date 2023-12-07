package org.example.springbootdeveloper.newChat.service;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.example.springbootdeveloper.newChat.domain.ChatMessage;
import org.example.springbootdeveloper.newChat.repository.ChatMessageRepository;
import org.example.springbootdeveloper.user.domain.User;
import org.example.springbootdeveloper.user.respository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@ServerEndpoint("/socket/chatt/{apply_id}")
public class WebSocketChat {
    private final UserRepository userRepository;
    private final ChatMessageRepository chatMessageRepository;

    private static Map<String, Set<Session>> channels = new HashMap<>();
    private static Logger logger = LoggerFactory.getLogger(WebSocketChat.class);

    public WebSocketChat(UserRepository userRepository, ChatMessageRepository chatMessageRepository) {
        this.userRepository = userRepository;
        this.chatMessageRepository = chatMessageRepository;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("apply_id") String channel) {
        logger.info("open session : {}, channel : {}", session.toString(), channel);

        channels.computeIfAbsent(channel, k -> Collections.synchronizedSet(new HashSet<>()))
                .add(session);

        logger.info("clients={}", channels);
    }

    @OnMessage
    public void onMessage(String jsonMessage, Session session, @PathParam("apply_id") Integer channel) throws IOException {
        logger.info("receive message : {}", jsonMessage);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonMessage);

        String sender = jsonNode.get("name").asText();
        String message = jsonNode.get("msg").asText();
        String date = jsonNode.get("date").asText();
        String senderId;
        Optional<User> userOptional = userRepository.findByNickname(sender);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            senderId = user.getUserId();
            chatMessageRepository.save( ChatMessage.builder()
                    .chatRoomId(channel)
                    .message(message)
                    .date(date)
                    .senderId(senderId)
                    .build());


        }


        logger.info("Parsed message - Sender: {}, Message: {}, Date: {}", sender, message, date);




        Set<Session> channelSessions = channels.get(channel);
        if (channelSessions != null) {
            for (Session s : channelSessions) {
                s.getBasicRemote().sendText(jsonMessage);
            }
        }
    }

    @OnClose
    public void onClose(Session session, @PathParam("apply_id") String channel) {
        logger.info("session close : {}", session);
        Set<Session> channelSessions = channels.get(channel);
        if (channelSessions != null) {
            channelSessions.remove(session);
        }
    }
}
