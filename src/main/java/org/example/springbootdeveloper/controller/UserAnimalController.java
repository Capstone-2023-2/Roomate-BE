package org.example.springbootdeveloper.controller;

import org.example.springbootdeveloper.domain.UserAnimal;
import org.example.springbootdeveloper.domain.UserStyle;
import org.example.springbootdeveloper.dto.AddUserRequest;
import org.example.springbootdeveloper.dto.AddUserStyleRequest;
import org.example.springbootdeveloper.service.AnimalService;
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
        return ResponseEntity.ok("User style successfully");
    }



}
