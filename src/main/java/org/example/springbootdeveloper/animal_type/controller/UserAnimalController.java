package org.example.springbootdeveloper.animal_type.controller;

import org.example.springbootdeveloper.animal_type.domain.UserAnimal;
import org.example.springbootdeveloper.animal_type.service.AnimalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
public class UserAnimalController {
    private final AnimalService animalService;

    public UserAnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping("/animal")
    public ResponseEntity<String> addUserStyle(Principal principal) {
        UserAnimal saveduserAnimal = animalService.save(principal.getName());
        return ResponseEntity.ok("User animal successfully");
    }

    @PostMapping("/test/animal")
    public ResponseEntity<String> addAnimalStyle() {
        UserAnimal saveduserAnimal = animalService.addSampleUserAnimal();
        return ResponseEntity.ok("User animal successfully");
    }

}
