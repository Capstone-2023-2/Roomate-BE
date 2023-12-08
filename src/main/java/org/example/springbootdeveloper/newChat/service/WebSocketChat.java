package org.example.springbootdeveloper.newChat.service;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.example.springbootdeveloper.newChat.repository.ChatMessageRepository;
import org.example.springbootdeveloper.user.respository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@ServerEndpoint("/socket/chatt/{apply_id}")
public class WebSocketChat {
    private static Map<String, Set<Session>> channels = new HashMap<>();
    private static Logger logger = LoggerFactory.getLogger(WebSocketChat.class);


    @OnOpen
    public void onOpen(Session session, @PathParam("apply_id") String channel) {
        logger.info("open session : {}, channel : {}", session.toString(), channel);

        channels.computeIfAbsent(channel, k -> Collections.synchronizedSet(new HashSet<>()))
                .add(session);

        logger.info("clients={}", channels);
    }

    @OnMessage
    public void onMessage(String jsonMessage, Session session, @PathParam("apply_id") String channel) throws IOException {
        logger.info("receive message : {}", jsonMessage);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonMessage);

        String sender = jsonNode.get("name").asText();
        String message = jsonNode.get("msg").asText();
        String date = jsonNode.get("date").asText();

        logger.info("Parsed message - Sender: {}, Message: {}, Date: {}", sender, message, date);

        // 이제 필요한 작업을 수행하면 됩니다.

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