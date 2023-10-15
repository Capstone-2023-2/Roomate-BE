package org.example.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.domain.UserStyle;
import org.example.springbootdeveloper.dto.AddUserStyleRequest;
import org.example.springbootdeveloper.service.StyleService;
import org.example.springbootdeveloper.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RequiredArgsConstructor
@RestController
public class UserStyleApiController {
    private final StyleService styleService;
    @PostMapping("/style")
    public ResponseEntity<String> addUserStyle(@RequestBody AddUserStyleRequest request, Principal principal) {
        UserStyle savedUserStyle = styleService.save(request, principal.getName());
        return ResponseEntity.ok("User style successfully");
    }


}
