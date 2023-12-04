package org.example.springbootdeveloper.animal_type.controller;

import org.example.springbootdeveloper.animal_type.domain.UserAnimal;
import org.example.springbootdeveloper.animal_type.repository.UserAnimalRepository;
import org.example.springbootdeveloper.animal_type.repository.UserStyleRepository;
import org.example.springbootdeveloper.animal_type.service.AnimalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
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
    public ResponseEntity<Map<String, Object>> nickname(Principal principal) {
        Optional<UserAnimal> userAnimalOptional = userAnimalRepository.findByUserId(principal.getName());
        Map<String, Object> response = new HashMap<>();

        if (userAnimalOptional.isPresent()) {
            UserAnimal userAnimal = userAnimalOptional.get();
            String animal = userAnimal.getAnimal();
            boolean sensitive = userAnimal.isSensitive();

            // Replace spaces with camelCase for animal names
            if ("polar bear".equals(animal)) {
                animal = "polarBear";
            } else if ("arctic fox".equals(animal)) {
                animal = "arcticFox";
            }

            response.put("animal", animal);
            response.put("sensitive", sensitive);
        } else {
            response.put("animal", "");
            response.put("sensitive", false);
        }

        return ResponseEntity.ok().body(response);
    }
/*
    @PostMapping("/test/animal")
    public ResponseEntity<String> addAnimalStyle() {
        UserAnimal saveduserAnimal = animalService.addSampleUserAnimal();
        return ResponseEntity.ok("User animal successfully");
    }

 */

}
