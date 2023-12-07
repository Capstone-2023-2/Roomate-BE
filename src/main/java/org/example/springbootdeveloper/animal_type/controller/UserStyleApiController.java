package org.example.springbootdeveloper.animal_type.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.animal_type.domain.UserAnimal;
import org.example.springbootdeveloper.animal_type.domain.UserStyle;
import org.example.springbootdeveloper.animal_type.dto.AddUserStyleRequest;
import org.example.springbootdeveloper.animal_type.service.AnimalService;
import org.example.springbootdeveloper.animal_type.service.StyleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RequiredArgsConstructor
@RestController
public class UserStyleApiController {
    private final StyleService styleService;
    private final AnimalService animalService;
    @PostMapping("/style")
    public ResponseEntity<String> addUserStyle(@RequestBody AddUserStyleRequest request, Principal principal) {
        UserStyle savedUserStyle = styleService.save(request, principal.getName());
        UserAnimal saveduserAnimal = animalService.save(principal.getName());
        return ResponseEntity.ok("User style successfully");
    }

    @GetMapping("/style")
    public ResponseEntity<Boolean> getUserStyle(Principal principal) {
        Boolean existUserStyle = styleService.doesUserStyleExist(principal.getName());
        return ResponseEntity.ok(existUserStyle);
    }



}
