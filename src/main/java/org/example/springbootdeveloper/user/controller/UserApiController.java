package org.example.springbootdeveloper.user.controller;

import org.example.springbootdeveloper.dorm_auth.domain.FileEntity;
import org.example.springbootdeveloper.user.domain.User;
import org.example.springbootdeveloper.user.dto.AddUserRequest;
import org.example.springbootdeveloper.user.respository.UserRepository;
import org.example.springbootdeveloper.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;


@RestController
public class UserApiController {

    private final UserService userService;
    private final UserRepository userRepository;
    public UserApiController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/api/hello")
    public String test() {
        return "Hello, world!";
    }

    @PostMapping("/user")
    public ResponseEntity<String> signup(@RequestBody AddUserRequest request) {
        userService.save(request); //회원 가입 메서드 호출
        return ResponseEntity.ok("User signed up successfully");
    }

    @GetMapping("/nickname")
    public ResponseEntity<String> nickname(Principal principal){
        Optional<User> userOptional = userRepository.findByUserId(principal.getName());
        String nickname;
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            nickname = user.getNickname();
        }
        else{
            nickname = "";
        }
        return ResponseEntity.ok().body(nickname);
    }

    /* 아이디, 닉네임, 이메일 중복 체크 */
    @GetMapping("/auth/id/{userId}/exists")
    public ResponseEntity<Boolean> checkUserIdDuplicate(@PathVariable String userId){
        return ResponseEntity.ok(userService.checkUserIdDuplication(userId));
    }

    @GetMapping("/auth/nickname/{nickname}/exists")
    public ResponseEntity<Boolean> checkNicknameDuplicate(@PathVariable String nickname){
        return ResponseEntity.ok(userService.checkNicknameDuplication(nickname));
    }

    @GetMapping("/auth/email/{email}/exists")
    public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable String email){
        return ResponseEntity.ok(userService.checkEmailDuplication(email));
    }

}
