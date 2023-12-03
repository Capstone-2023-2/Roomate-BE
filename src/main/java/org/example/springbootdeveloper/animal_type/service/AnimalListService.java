package org.example.springbootdeveloper.animal_type.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.animal_type.domain.UserAnimal;
import org.example.springbootdeveloper.animal_type.domain.UserStyle;
import org.example.springbootdeveloper.animal_type.dto.AnimalListDTO;
import org.example.springbootdeveloper.animal_type.dto.UserHeartDTO;
import org.example.springbootdeveloper.animal_type.repository.UserAnimalRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AnimalListService {
    private final UserAnimalRepository userAnimalRepository;
    public UserAnimal loadUserByUsername(String userId){
        return userAnimalRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException(userId));
    }

    public AnimalListDTO makeAnimalListDTO(String userId) {
        String name;
        String features;
        List<String> wellMatchedRoommates = new ArrayList<>();
        List<String> incompatibleRoommates = new ArrayList<>();

        UserAnimal userAnimal = loadUserByUsername(userId);
        name = userAnimal.getAnimal();

        switch (name) {
            case "penguin":
                features = "Enjoys welcoming mornings as a morning person, seeks intimate relationships with roommates, prefers winter over summer";
                wellMatchedRoommates = Arrays.asList("penguin", "dog");
                incompatibleRoommates = Arrays.asList("cat", "arctic fox");
                break;
            case "dog":
                features = "Enjoys welcoming mornings as a morning person, seeks intimate relationships with roommates, prefers summer over winter";
                wellMatchedRoommates = Arrays.asList("dog", "penguin");
                incompatibleRoommates = Arrays.asList("arctic fox", "cat");
                break;
            case "polar bear":
                features = "Enjoys welcoming mornings as a morning person, values alone time, prefers winter over summer";
                wellMatchedRoommates = Arrays.asList("polar bear", "rabbit");
                incompatibleRoommates = Arrays.asList("quokka", "wolf");
                break;
            case "rabbit":
                features = "Enjoys welcoming mornings as a morning person, values alone time, prefers summer over winter";
                wellMatchedRoommates = Arrays.asList("rabbit", "polar bear");
                incompatibleRoommates = Arrays.asList("wolf", "quokka");
                break;
            case "wolf":
                features = "Appreciates the quiet of the night as an evening person, seeks intimate relationships with roommates, prefers winter over summer";
                wellMatchedRoommates = Arrays.asList("wolf", "quokka");
                incompatibleRoommates = Arrays.asList("rabbit", "polar bear");
                break;
            case "quokka":
                features = "Appreciates the quiet of the night as an evening person, seeks intimate relationships with roommates, prefers summer over winter";
                wellMatchedRoommates = Arrays.asList("quokka", "wolf");
                incompatibleRoommates = Arrays.asList("polar bear", "rabbit");
                break;
            case "arctic fox":
                features = "Appreciates the quiet of the night as an evening person, seeks intimate relationships with roommates, prefers winter over summer";
                wellMatchedRoommates = Arrays.asList("arctic fox", "cat");
                incompatibleRoommates = Arrays.asList("dog", "penguin");
                break;
            case "cat":
                features = "Appreciates the quiet of the night as an evening person, seeks intimate relationships with roommates, prefers summer over winter";
                wellMatchedRoommates = Arrays.asList("cat", "arctic fox");
                incompatibleRoommates = Arrays.asList("penguin", "dog");
                break;
            default:
                features = ""; // Set default value
                break;
        }

        AnimalListDTO animalListDTO = AnimalListDTO.builder()
                .name(name)
                .features(features)
                .wellMatchedRoommates(wellMatchedRoommates)
                .incompatibleRoommates(incompatibleRoommates)
                .build();
        return animalListDTO;
    }

}
