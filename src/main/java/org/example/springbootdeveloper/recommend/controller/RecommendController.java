package org.example.springbootdeveloper.recommend.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.animal_type.domain.UserAnimal;
import org.example.springbootdeveloper.connection.WebClientServiceImpl;
import org.example.springbootdeveloper.recommend.domain.UserDetail;
import org.example.springbootdeveloper.recommend.dto.AddUserDetailRequest;
import org.example.springbootdeveloper.recommend.dto.UserCardDTO;
import org.example.springbootdeveloper.recommend.service.RecommendService;
import org.example.springbootdeveloper.user.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class RecommendController {
    private final RecommendService recommendService;


    @GetMapping("/card")
    public ResponseEntity<Map<String, UserCardDTO>> addUserCard(Principal principal) {

        Map<String, UserCardDTO> cardList = recommendService.getCards(principal.getName());


        return ResponseEntity.ok(cardList);
    }

    @GetMapping("/card/new")
    public ResponseEntity<Map<String, UserCardDTO>> addUserNewCard(Principal principal) {

        Map<String, UserCardDTO> cardList = recommendService.getNewCards(principal.getName());
        return ResponseEntity.ok(cardList);
    }




}


