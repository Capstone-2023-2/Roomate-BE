package org.example.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.domain.User;
import org.example.springbootdeveloper.domain.UserAnimal;
import org.example.springbootdeveloper.domain.UserStyle;
import org.example.springbootdeveloper.dto.AddUserAnimalRequest;
import org.example.springbootdeveloper.dto.AddUserStyleRequest;
import org.example.springbootdeveloper.repository.UserAnimalRepository;
import org.example.springbootdeveloper.repository.UserStyleRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AnimalService {
    private final UserAnimalRepository userAnimalRepository;
    private final UserStyleRepository userStyleRepository;


    public UserStyle loadUserByUsername(String userId){
        return userStyleRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException(userId));
    }

    public UserAnimal save(String userId){

        UserStyle userStyle = loadUserByUsername(userId);
        boolean userSensitive = true;
        String selectedAnimal = "";
        String[] animals = {"dog", "penguin", "rabbit", "polar bear", "quokka", "wolf", "cat", "arctic fox"};

        boolean active = true; //외향성이면 true, 내향성이면 false
        boolean hot_cold = true; //여름 선호형(추위 기피형)이면 true, 겨울 선호형(더위 기피형)이면 false

        // 변수들을 배열에 담기
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
        double day_average = (userStyles[0] + userStyles[1]) / 2.0;
        boolean day_night = (day_average <= 3);

        if(userStyles[18]== 1){active = true;}
        else{active = false;}

        if (userStyles[21] == 1) {
            hot_cold = true; //여름
        } else if (userStyles[21] == 3) {
            hot_cold = false; //겨울
        } else if (userStyles[21] == 2 || userStyles[21] == 0) {
            if (userStyles[22] == 1) {
                hot_cold = true; //여름
            } else if (userStyles[22] == 3) {
                hot_cold = false; //겨울
            }
        }


        if (day_night && active && hot_cold) {
            selectedAnimal = animals[0];
        } else if (day_night && active && !hot_cold) {
            selectedAnimal = animals[1];
        } else if (day_night && !active && hot_cold) {
            selectedAnimal = animals[2];
        } else if (day_night && !active && !hot_cold) {
            selectedAnimal = animals[3];
        } else if (!day_night && active && hot_cold) {
            selectedAnimal = animals[4];
        } else if (!day_night && active && !hot_cold) {
            selectedAnimal = animals[5];
        } else if (!day_night && !active && hot_cold) {
            selectedAnimal = animals[6];
        } else if (!day_night && !active && !hot_cold) {
            selectedAnimal = animals[7];
        }


        UserAnimal userAnimal = UserAnimal.builder()
                .userId(userId)
                .sensitive(userSensitive)
                .animal(selectedAnimal)
                .build();
        return userAnimalRepository.save(userAnimal);
    }


}
