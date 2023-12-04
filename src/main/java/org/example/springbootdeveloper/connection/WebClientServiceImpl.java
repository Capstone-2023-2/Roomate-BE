package org.example.springbootdeveloper.connection;

import lombok.extern.slf4j.Slf4j;
import org.example.springbootdeveloper.animal_type.domain.UserStyle;
import org.example.springbootdeveloper.animal_type.repository.UserStyleRepository;
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
    private final UserStyleRepository userStyleRepository;


    public WebClientServiceImpl(UserRepository userRepository, UserStyleRepository userStyleRepository) {
        this.userRepository = userRepository;
        this.userStyleRepository = userStyleRepository;
    }

    public  Map<String, Object> post(String userId) {
        UserStyle userStyle = loadUserByUsername(userId);
        int[] userStyles = {
                userStyle.getBedtimeScore(),
                userStyle.getWakeupScore(),
                userStyle.getWakeupSensitivity(),
                userStyle.getCleaningScore(),
                userStyle.getCleaningSensitivity(),
                userStyle.getFoodScore(),
                userStyle.getFoodSensitivity(),
                userStyle.getCigaretteScore(),
                userStyle.getStudyScore(),
                userStyle.getStudySensitivity(),
                userStyle.getNotebookScore(),
                userStyle.getNotebookSensitivity(),
                userStyle.getAlarmScore(),
                userStyle.getAlarmSensitivity(),
                userStyle.getLatestudyScore(),
                userStyle.getLatestudySensitivity(),
                userStyle.getSnoringScore(),
                userStyle.getSnoringSensitivity(),
                userStyle.getFriendlyScore(),
                userStyle.getInhomeScore(),
                userStyle.getInhomeSensitivity(),
                userStyle.getColdOrHot(),
                userStyle.getSummerOrWinter()
        };

        Map<String, Object> bodyMap = new HashMap<>();

            bodyMap.put("userId", userId);
            bodyMap.put("bedtimeScore", userStyles[0]);
            bodyMap.put("wakeupScore", userStyles[1]);
            bodyMap.put("wakeupSensitivity", userStyles[2]);
            bodyMap.put("cleaningScore", userStyles[3]);
            bodyMap.put("cleaningSensitivity", userStyles[4]);
            bodyMap.put("foodScore", userStyles[5]);
            bodyMap.put("foodSensitivity", userStyles[6]);
            bodyMap.put("cigaretteScore", userStyles[7]);
            bodyMap.put("studyScore", userStyles[8]);
            bodyMap.put("studySensitivity", userStyles[9]);
            bodyMap.put("notebookScore", userStyles[10]);
            bodyMap.put("notebookSensitivity", userStyles[11]);
            bodyMap.put("alarmScore", userStyles[12]);
            bodyMap.put("alarmSensitivity", userStyles[13]);
            bodyMap.put("latestudyScore", userStyles[14]);
            bodyMap.put("latestudySensitivity", userStyles[15]);
            bodyMap.put("snoringScore", userStyles[16]);
            bodyMap.put("snoringSensitivity", userStyles[17]);
            bodyMap.put("friendlyScore", userStyles[18]);
            bodyMap.put("inhomeScore", userStyles[19]);
            bodyMap.put("inhomeSensitivity", userStyles[20]);
            bodyMap.put("coldOrHot", userStyles[21]);
            bodyMap.put("summerOrWinter", userStyles[22]);


        // webClient 기본 설정
        WebClient webClient =
                WebClient
                        .builder()
                        .baseUrl("https://f1e5-58-76-166-214.ngrok-free.app")
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

    public UserStyle loadUserByUsername(String userId){
        return userStyleRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException(userId));
    }


    public Map<String, Object> postUpdate(String userId) {
        // Create a new request body for the new API endpoint
        Map<String, Object> newBodyMap = new HashMap<>();
        newBodyMap.put("userId", userId);

        // Populate newBodyMap with the required parameters for the new endpoint

        // Update WebClient configuration if needed
        WebClient webClient =
                WebClient
                        .builder()
                        .baseUrl("https://f1e5-58-76-166-214.ngrok-free.app")
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