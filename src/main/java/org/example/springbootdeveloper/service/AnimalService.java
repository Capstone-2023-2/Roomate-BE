package org.example.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
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
    public UserAnimal save(AddUserAnimalRequest request, String userId){
        return userAnimalRepository.save(request.toEntity(userId));
    }
}
