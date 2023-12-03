package org.example.springbootdeveloper.connection;

import lombok.extern.slf4j.Slf4j;
import org.example.springbootdeveloper.animal_type.domain.UserAnimal;
import org.example.springbootdeveloper.user.domain.User;
import org.example.springbootdeveloper.user.respository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class WebClientServiceImpl {
    private final UserRepository userRepository;

    public WebClientServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public  Map<String, Object> post(String userId) {


        Map<String, Object> bodyMap = new HashMap<>();
        if(userId == "1111") {

            bodyMap.put("userId", "1111");
            bodyMap.put("bedtimeScore", 3);
            bodyMap.put("wakeupScore", 3);
            bodyMap.put("wakeupSensitivity", 1);
            bodyMap.put("cleaningScore", 3);
            bodyMap.put("cleaningSensitivity", 3);
            bodyMap.put("foodScore", 2);
            bodyMap.put("foodSensitivity", 2);
            bodyMap.put("cigaretteScore", 3);
            bodyMap.put("studyScore", 2);
            bodyMap.put("studySensitivity", 1);
            bodyMap.put("notebookScore", 3);
            bodyMap.put("notebookSensitivity", 2);
            bodyMap.put("alarmScore", 3);
            bodyMap.put("alarmSensitivity", 2);
            bodyMap.put("latestudyScore", 3);
            bodyMap.put("latestudySensitivity", 1);
            bodyMap.put("snoringScore", 3);
            bodyMap.put("snoringSensitivity", 2);
            bodyMap.put("friendlyScore", 3);
            bodyMap.put("inhomeScore", 2);
            bodyMap.put("inhomeSensitivity", 1);
            bodyMap.put("coldOrHot", 1);
            bodyMap.put("summerOrWinter", 1);
        }
        else{
            bodyMap.put("userId", "2222");
            bodyMap.put("bedtimeScore", 4);
            bodyMap.put("wakeupScore", 3);
            bodyMap.put("wakeupSensitivity", 1);
            bodyMap.put("cleaningScore", 2);
            bodyMap.put("cleaningSensitivity", 2);
            bodyMap.put("foodScore", 1);
            bodyMap.put("foodSensitivity", 1);
            bodyMap.put("cigaretteScore", 1);
            bodyMap.put("studyScore", 3);
            bodyMap.put("studySensitivity", 3);
            bodyMap.put("notebookScore", 3);
            bodyMap.put("notebookSensitivity", 2);
            bodyMap.put("alarmScore", 2);
            bodyMap.put("alarmSensitivity", 1);
            bodyMap.put("latestudyScore", 3);
            bodyMap.put("latestudySensitivity", 3);
            bodyMap.put("snoringScore", 3);
            bodyMap.put("snoringSensitivity", 1);
            bodyMap.put("friendlyScore", 1);
            bodyMap.put("inhomeScore", 2);
            bodyMap.put("inhomeSensitivity", 2);
            bodyMap.put("coldOrHot", 0);
            bodyMap.put("summerOrWinter", 3);


        }

        // webClient 기본 설정
        WebClient webClient =
                WebClient
                        .builder()
                        .baseUrl("https://490c-58-76-166-214.ngrok-free.app")
                        .build();

        // api 요청
        Map<String, Object> response =
                webClient
                        .post()
                        .uri("/8m_rec")
                        .bodyValue(bodyMap)
                        .retrieve()
                        .bodyToMono(Map.class)
                        .block();

        return response;

    }

    public Map<String, Object> postUpdate(String userId) {
        // Create a new request body for the new API endpoint
        Map<String, Object> newBodyMap = new HashMap<>();
        if(userId == "1111") {

            newBodyMap.put("userId", "1111");
        }
        else{
            newBodyMap.put("userId", "2222");
        }

        // Populate newBodyMap with the required parameters for the new endpoint

        // Update WebClient configuration if needed
        WebClient webClient =
                WebClient
                        .builder()
                        .baseUrl("https://490c-58-76-166-214.ngrok-free.app")
                        // Add any additional configuration if needed
                        .build();

        // Make the API request for the new endpoint
        Map<String, Object> response =
                webClient
                        .post()
                        .uri("/up_rec") // Update the URI to the new endpoint
                        .bodyValue(newBodyMap)
                        .retrieve()
                        .bodyToMono(Map.class)
                        .block();

        return response;
    }
}