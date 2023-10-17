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
    private static final String[] animals = {"dog", "penguin", "rabbit", "polar bear", "quokka", "wolf", "cat", "arctic fox"};


    public UserStyle loadUserByUsername(String userId){
        return userStyleRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException(userId));
    }

    public UserAnimal save(String userId){

        UserStyle userStyle = loadUserByUsername(userId);
        boolean userSensitive = true;
        String animal = animals[0];
        boolean day_night = true; //아침형이면 true, 저녁형이면 false
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


        if (userStyles[0] == 1)
        {
            animal = "rabbit";


        }














        UserAnimal userAnimal = UserAnimal.builder()
                .userId(userId)
                .sensitive(userSensitive)
                .animal(animal)
                .build();
        return userAnimalRepository.save(userAnimal);
    }


}
