package org.example.springbootdeveloper.recommend.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.recommend.domain.UserDetail;
import org.example.springbootdeveloper.recommend.domain.UserStar;
import org.example.springbootdeveloper.recommend.dto.AddUserDetailRequest;
import org.example.springbootdeveloper.recommend.dto.AddUserStarRequest;
import org.example.springbootdeveloper.recommend.service.DetailService;
import org.example.springbootdeveloper.recommend.service.StarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
public class UserStarApiController {
    private final StarService starService;

    @PostMapping("/star/{starId}") //찜 추가
    public ResponseEntity<String> addUserStar(@PathVariable String starId, Principal principal) {
        UserStar savedUserStar = starService.save(starId, principal.getName());
        return ResponseEntity.ok("User star successfully");
    }

    @DeleteMapping("/star/{starId}")
    public ResponseEntity<String> deleteUserStar(@PathVariable String starId, Principal principal) {
        boolean deleted = starService.delete(starId, principal.getName());

        if (deleted) {
            return ResponseEntity.ok("User star deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User star not found");
        }
    }
}
