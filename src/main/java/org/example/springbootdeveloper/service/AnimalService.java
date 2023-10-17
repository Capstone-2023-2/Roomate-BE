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
        String animal = "";
        if (userStyle.getBedtimeScore() == 1)
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
