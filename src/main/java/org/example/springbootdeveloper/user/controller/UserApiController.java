package org.example.springbootdeveloper.user.controller;

import org.example.springbootdeveloper.dorm_auth.domain.FileEntity;
import org.example.springbootdeveloper.user.domain.User;
import org.example.springbootdeveloper.user.dto.AddUserRequest;
import org.example.springbootdeveloper.user.dto.UserStatusUpdateRequest;
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

    @GetMapping("/status")
    public boolean getUserStatus(Principal principal) {
        // principal.getName()을 사용하여 현재 사용자의 userId를 가져옴
        String userId = principal.getName();

        // userRepository에서 userId로 사용자를 찾음
        Optional<User> userOptional = userRepository.findByUserId(userId);

        // 사용자가 존재하고, status가 true인 경우 true를 반환, 그렇지 않으면 false를 반환
        return userOptional.map(User::isStatus).orElse(false);
    }

    @PostMapping("/status")
    public boolean updateUserStatus(Principal principal, @RequestBody UserStatusUpdateRequest request) {
        String userId = principal.getName();
        Optional<User> userOptional = userRepository.findByUserId(userId);

        // 사용자가 존재할 경우 status 업데이트
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setStatus(request.isNewStatus());
            userRepository.save(user);
            return true;
        }

        // 사용자가 존재하지 않으면 업데이트 실패
        return false;
    }
    @GetMapping("/api/hello")
    public String test() {
        return "Hello, world!";
    }

    @PostMapping("/api/user")
    public ResponseEntity<String> signup(@RequestBody AddUserRequest request) {
        userService.save(request); //회원 가입 메서드 호출
        return ResponseEntity.ok("User signed up successfully");
    }

    @GetMapping("/nickname")
    //@CrossOrigin(origins = {"http://ANIroomi-env.eba-rj7upyms.ap-northeast-2.elasticbeanstalk.com", "http://localhost:3000"}, allowedHeaders = "*")
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
