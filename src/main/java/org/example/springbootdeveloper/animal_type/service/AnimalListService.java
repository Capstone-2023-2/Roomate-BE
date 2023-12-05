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
        Boolean sensitive;
        List<String> wellMatchedRoommates = new ArrayList<>();
        List<String> incompatibleRoommates = new ArrayList<>();

        UserAnimal userAnimal = loadUserByUsername(userId);
        name = userAnimal.getAnimal();
        sensitive = userAnimal.isSensitive();

        switch (name) {
            case "penguin":
                features = "상쾌한 아침을 맞이하는 아침형 인간으로, 룸메이트와 친밀한 관계를 얻길 원하고 여름보단 겨울을 좋아하는 성향이 있다.";
                wellMatchedRoommates = Arrays.asList("penguin", "dog");
                incompatibleRoommates = Arrays.asList("cat", "arctic fox");
                break;
            case "dog":
                features = "상쾌한 아침을 맞이하는 아침형 인간으로, 룸메이트와 친밀한 관계를 얻길 원하고 겨울보단 여름을 좋아하는 성향이 있다.";
                wellMatchedRoommates = Arrays.asList("dog", "penguin");
                incompatibleRoommates = Arrays.asList("arctic fox", "cat");
                break;
            case "polar bear":
                features = "상쾌한 아침을 맞이하는 아침형 인간으로, 혼자만의 시간을 중요하게 생각하고 여름보단 겨울을 좋아하는 성향이 있다.";
                wellMatchedRoommates = Arrays.asList("polar bear", "rabbit");
                incompatibleRoommates = Arrays.asList("quokka", "wolf");
                break;
            case "rabbit":
                features = "상쾌한 아침을 맞이하는 아침형 인간으로, 혼자만의 시간을 중요하게 생각하고 겨울보단 여름을 좋아하는 성향이 있다.";
                wellMatchedRoommates = Arrays.asList("rabbit", "polar bear");
                incompatibleRoommates = Arrays.asList("wolf", "quokka");
                break;
            case "wolf":
                features = "새벽의 고요함을 즐기는 저녁형 인간으로, 룸메이트와 친밀한 관계를 얻길 원하고 여름보단 겨울을 좋아하는 성향이 있다.";
                wellMatchedRoommates = Arrays.asList("wolf", "quokka");
                incompatibleRoommates = Arrays.asList("rabbit", "polar bear");
                break;
            case "quokka":
                features = "새벽의 고요함을 즐기는 저녁형 인간으로, 룸메이트와 친밀한 관계를 얻길 원하고 겨울보단 여름을 좋아하는 성향이 있다.";
                wellMatchedRoommates = Arrays.asList("quokka", "wolf");
                incompatibleRoommates = Arrays.asList("polar bear", "rabbit");
                break;
            case "arctic fox":
                features = "새벽의 고요함을 즐기는 저녁형 인간으로, 혼자만의 시간을 중요하게 생각하고 여름보단 겨울을 좋아하는 성향이 있다.";
                wellMatchedRoommates = Arrays.asList("arctic fox", "cat");
                incompatibleRoommates = Arrays.asList("dog", "penguin");
                break;
            case "cat":
                features = "새벽의 고요함을 즐기는 저녁형 인간으로, 혼자만의 시간을 중요하게 생각하고 겨울보단 여름을 좋아하는 성향이 있다.";
                wellMatchedRoommates = Arrays.asList("cat", "arctic fox");
                incompatibleRoommates = Arrays.asList("penguin", "dog");
                break;
            default:
                features = ""; // Set default value
                break;
        }

        AnimalListDTO animalListDTO = AnimalListDTO.builder()
                .sensitive(sensitive)
                .name(name)
                .features(features)
                .wellMatchedRoommates(wellMatchedRoommates)
                .incompatibleRoommates(incompatibleRoommates)
                .build();
        return animalListDTO;
    }

}
