package org.example.springbootdeveloper.animal_type.controller;

import org.example.springbootdeveloper.animal_type.domain.UserAnimal;
import org.example.springbootdeveloper.animal_type.repository.UserAnimalRepository;
import org.example.springbootdeveloper.animal_type.repository.UserStyleRepository;
import org.example.springbootdeveloper.animal_type.service.AnimalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;


@RestController
public class UserAnimalController {
    private final AnimalService animalService;
    private final UserAnimalRepository userAnimalRepository;
    private final UserStyleRepository userStyleRepository;

    public UserAnimalController(AnimalService animalService, UserAnimalRepository userAnimalRepository, UserStyleRepository userStyleRepository) {
        this.animalService = animalService;
        this.userAnimalRepository = userAnimalRepository;
        this.userStyleRepository = userStyleRepository;
    }

    @PostMapping("/animal")
    public ResponseEntity<String> addUserStyle(Principal principal) {
        UserAnimal saveduserAnimal = animalService.save(principal.getName());
        return ResponseEntity.ok("User animal successfully");
    }

    @PostMapping("/animal/{userId}")
    public ResponseEntity<String> addUserStyle(@PathVariable String userId) {
        UserAnimal saveduserAnimal = animalService.save(userId);
        return ResponseEntity.ok("User animal successfully");
    }

    @GetMapping("/animal")
    public ResponseEntity<String> nickname(Principal principal) {
        Optional<UserAnimal> userAnimalOptional = userAnimalRepository.findByUserId(principal.getName());
        String animal;
        if (userAnimalOptional.isPresent()) {
            UserAnimal userAnimal = userAnimalOptional.get();

            animal = userAnimal.getAnimal();

            // 띄어쓰기가 있는 경우 모든 단어를 붙여서 반환
            if(animal == "polar bear"){
                animal = "polarBear";
            }
            else if(animal == "arctic fox"){
                animal = "arcticFox";
            }

        } else {
            animal = "";
        }
        return ResponseEntity.ok().body(animal);
    }


    @PostMapping("/test/animal")
    public ResponseEntity<String> addAnimalStyle() {
        UserAnimal saveduserAnimal = animalService.addSampleUserAnimal();
        return ResponseEntity.ok("User animal successfully");
    }

}
