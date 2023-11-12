package org.example.springbootdeveloper.recommend.controller;


import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.animal_type.domain.UserStyle;
import org.example.springbootdeveloper.animal_type.dto.AddUserStyleRequest;
import org.example.springbootdeveloper.recommend.domain.UserDetail;
import org.example.springbootdeveloper.recommend.dto.AddUserDetailRequest;
import org.example.springbootdeveloper.recommend.service.DetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
public class UserDetailApiController {
    private final DetailService detailService;

    @PostMapping("/detail")
    public ResponseEntity<String> addUserStyle(@RequestBody AddUserDetailRequest request, Principal principal) {
        UserDetail savedUserDetail = detailService.save(request, principal.getName());
        return ResponseEntity.ok("User detail successfully");
    }
}
