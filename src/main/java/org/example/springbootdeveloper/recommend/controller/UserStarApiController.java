package org.example.springbootdeveloper.recommend.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.recommend.domain.UserDetail;
import org.example.springbootdeveloper.recommend.domain.UserStar;
import org.example.springbootdeveloper.recommend.dto.AddUserDetailRequest;
import org.example.springbootdeveloper.recommend.dto.AddUserStarRequest;
import org.example.springbootdeveloper.recommend.dto.UserCardDTO;
import org.example.springbootdeveloper.recommend.service.DetailService;
import org.example.springbootdeveloper.recommend.service.StarService;
import org.example.springbootdeveloper.user.domain.User;
import org.example.springbootdeveloper.user.respository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class UserStarApiController {
    private final StarService starService;
    private final UserRepository userRepository;

    @PostMapping("/star/{nickname}") //찜 추가
    public ResponseEntity<String> addUserStar(@PathVariable String nickname, Principal principal) {
        Optional<User> userOptional = userRepository.findByNickname(nickname);
        String starId;
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            starId = user.getUserId();
            UserStar savedUserStar = starService.save(starId, principal.getName());
            return ResponseEntity.ok("User star successfully");
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/star/{nickname}")
    public ResponseEntity<String> deleteUserStar(@PathVariable String nickname, Principal principal) {
        Optional<User> userOptional = userRepository.findByNickname(nickname);
        String starId;
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            starId = user.getUserId();
            boolean deleted = starService.delete(starId, principal.getName());

            if (deleted) {
                return ResponseEntity.ok("User star deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User star not found");
            }
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/list/star")
    public ResponseEntity<List<UserCardDTO>> getAllUserDetails(Principal principal){
        List<UserCardDTO> userCardDTOs = starService.showStarList(principal.getName());
        return ResponseEntity.ok(userCardDTOs);
    }


}
