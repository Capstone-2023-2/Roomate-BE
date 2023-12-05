package org.example.springbootdeveloper.animal_type.controller;

import org.example.springbootdeveloper.animal_type.dto.AnimalListDTO;
import org.example.springbootdeveloper.animal_type.dto.StyleIconDTO;
import org.example.springbootdeveloper.animal_type.dto.UserHeartDTO;
import org.example.springbootdeveloper.animal_type.service.*;
import org.example.springbootdeveloper.user.domain.User;
import org.example.springbootdeveloper.user.respository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
public class AnimalPageController {

    private final HeartService heartService;
    private final TextStyleService textStyleService;
    private final IconService iconService;
    private final AnimalListService animalListService;

    private final UserRepository userRepository;

    public AnimalPageController(HeartService heartService, TextStyleService textStyleService, IconService iconService, AnimalListService animalListService, UserRepository userRepository) {
        this.heartService = heartService;
        this.textStyleService = textStyleService;
        this.iconService = iconService;
        this.animalListService = animalListService;
        this.userRepository = userRepository;
    }

    @GetMapping("/heart") //자기 자신 용
    public ResponseEntity<UserHeartDTO> getUserHeart(Principal principal) {
        UserHeartDTO userHeartDTO = heartService.makeUserHeart(principal.getName());
        return ResponseEntity.ok()
                .body(userHeartDTO);
    }

    @GetMapping("/heart/{nickname}") //상대방거 볼 때
    public ResponseEntity<UserHeartDTO> getUserHeart(@PathVariable String nickname) {
        Optional<User> userOptional = userRepository.findByNickname(nickname);
        UserHeartDTO userHeartDTO;
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            userHeartDTO = heartService.makeUserHeart(user.getUserId());
            return ResponseEntity.ok().body(userHeartDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/style/text") //자기 자신 용
    public ResponseEntity<String> getUserStyleText(Principal principal) {
        String userStyleText = textStyleService.make_lifestyle_text(principal.getName());
        return ResponseEntity.ok()
                .body(userStyleText);
    }
    @GetMapping("/style/text/{nickname}") //자기 자신 용
    public ResponseEntity<String> getUserStyleText(@PathVariable String nickname) {
        Optional<User> userOptional = userRepository.findByNickname(nickname);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String userStyleText = textStyleService.make_lifestyle_text(user.getUserId());
            return ResponseEntity.ok()
                    .body(userStyleText);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/style/icon") //자기 자신 용
    public ResponseEntity<StyleIconDTO> getUserIcon(Principal principal) {
        StyleIconDTO styleIconDTO =iconService.makeUserIcon(principal.getName());
        return ResponseEntity.ok()
                .body(styleIconDTO);
    }

    @GetMapping("/style/icon/{nickname}") //상대방거 볼 때
    public ResponseEntity<StyleIconDTO> getUserIcon(@PathVariable String nickname) {
        Optional<User> userOptional = userRepository.findByNickname(nickname);
        StyleIconDTO styleIconDTO;
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            styleIconDTO = iconService.makeUserIcon(user.getUserId());
            return ResponseEntity.ok()
                    .body(styleIconDTO);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/style/animal") //자기 자신 용
    public ResponseEntity<AnimalListDTO> getAnimalList(Principal principal) {
        AnimalListDTO animalListDTO = animalListService.makeAnimalListDTO(principal.getName());
        return ResponseEntity.ok()
                .body(animalListDTO);
    }

    @GetMapping("/style/animal/{nickname}") //상대방거 볼 때
    public ResponseEntity<AnimalListDTO> getAnimalList(@PathVariable String nickname) {
        Optional<User> userOptional = userRepository.findByNickname(nickname);
        AnimalListDTO animalListDTO;
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            animalListDTO = animalListService.makeAnimalListDTO(user.getUserId());
            return ResponseEntity.ok()
                    .body(animalListDTO);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }



}