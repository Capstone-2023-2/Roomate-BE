package org.example.springbootdeveloper.recommend.controller;


import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.animal_type.domain.UserStyle;
import org.example.springbootdeveloper.animal_type.dto.AddUserStyleRequest;
import org.example.springbootdeveloper.dorm_auth.domain.FileEntity;
import org.example.springbootdeveloper.dorm_auth.service.FileSendService;
import org.example.springbootdeveloper.recommend.domain.UserDetail;
import org.example.springbootdeveloper.recommend.dto.AddUserDetailRequest;
import org.example.springbootdeveloper.recommend.service.DetailService;
import org.example.springbootdeveloper.user.domain.User;
import org.example.springbootdeveloper.user.respository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class UserDetailApiController {
    private final DetailService detailService;
    private final UserRepository userRepository;

    @PostMapping("/detail")
    public ResponseEntity<String> addUserDetail(@RequestBody AddUserDetailRequest request, Principal principal) {
        Optional<User> userOptional = userRepository.findByUserId(principal.getName());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            char gender = user.getGender();
            UserDetail savedUserDetail = detailService.save(request, principal.getName(), gender);
        }

        return ResponseEntity.ok("User detail successfully");
    }

    @GetMapping("/list/total")
    public ResponseEntity<List<UserDetail>> getAllUserDetails(Principal principal){
        List<UserDetail> userDetails = detailService.showFilteredUserList(principal.getName());
        return ResponseEntity.ok(userDetails);
    }
/*
    @GetMapping("/list/recommend")
    public ResponseEntity<List<UserDetail>> getAllUserDetails(Principal principal){
        List<UserDetail> userDetails = detailService.showFilteredUserList(principal.getName());
        return ResponseEntity.ok(userDetails);
    }

 */

}
