package org.example.springbootdeveloper.animal_type.controller;

import org.example.springbootdeveloper.animal_type.dto.AnimalListDTO;
import org.example.springbootdeveloper.animal_type.dto.StyleIconDTO;
import org.example.springbootdeveloper.animal_type.dto.UserHeartDTO;
import org.example.springbootdeveloper.animal_type.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AnimalPageController {

    private final HeartService heartService;
    private final TextStyleService textStyleService;
    private final IconService iconService;
    private final AnimalListService animalListService;

    public AnimalPageController(HeartService heartService, TextStyleService textStyleService, IconService iconService, AnimalListService animalListService) {
        this.heartService = heartService;
        this.textStyleService = textStyleService;
        this.iconService = iconService;
        this.animalListService = animalListService;
    }

    @GetMapping("/heart") //자기 자신 용
    public ResponseEntity<UserHeartDTO> getUserHeart(Principal principal) {
        UserHeartDTO userHeartDTO = heartService.makeUserHeart(principal.getName());
        return ResponseEntity.ok()
                .body(userHeartDTO);
    }

    @GetMapping("/heart/{userId}") //상대방거 볼 때
    public ResponseEntity<UserHeartDTO> getUserHeart(@PathVariable String userId) {
        UserHeartDTO userHeartDTO = heartService.makeUserHeart(userId);
        return ResponseEntity.ok()
                .body(userHeartDTO);
    }

    @GetMapping("/style/text") //자기 자신 용
    public ResponseEntity<String> getUserStyleText(Principal principal) {
        String userStyleText = textStyleService.make_lifestyle_text(principal.getName());
        return ResponseEntity.ok()
                .body(userStyleText);
    }
    @GetMapping("/style/text/{userId}") //자기 자신 용
    public ResponseEntity<String> getUserStyleText(@PathVariable String userId) {
        String userStyleText = textStyleService.make_lifestyle_text(userId);
        return ResponseEntity.ok()
                .body(userStyleText);
    }

    @GetMapping("/style/icon") //자기 자신 용
    public ResponseEntity<StyleIconDTO> getUserIcon(Principal principal) {
        StyleIconDTO styleIconDTO =iconService.makeUserIcon(principal.getName());
        return ResponseEntity.ok()
                .body(styleIconDTO);
    }

    @GetMapping("/style/icon/{userId}") //상대방거 볼 때
    public ResponseEntity<StyleIconDTO> getUserIcon(@PathVariable String userId) {
       StyleIconDTO styleIconDTO =iconService.makeUserIcon(userId);
        return ResponseEntity.ok()
                .body(styleIconDTO);
    }

    @GetMapping("/style/animal") //자기 자신 용
    public ResponseEntity<AnimalListDTO> getAnimalList(Principal principal) {
        AnimalListDTO animalListDTO = animalListService.makeAnimalListDTO(principal.getName());
        return ResponseEntity.ok()
                .body(animalListDTO);
    }

    @GetMapping("/style/animal/{userId}") //상대방거 볼 때
    public ResponseEntity<AnimalListDTO> getAnimalList(@PathVariable String userId) {
        AnimalListDTO animalListDTO = animalListService.makeAnimalListDTO(userId);
        return ResponseEntity.ok()
                .body(animalListDTO);
    }



}