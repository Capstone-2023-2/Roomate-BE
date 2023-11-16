package org.example.springbootdeveloper.test;

import org.example.springbootdeveloper.animal_type.domain.UserStyle;
import org.example.springbootdeveloper.animal_type.repository.UserStyleRepository;
import org.example.springbootdeveloper.recommend.domain.UserDetail;
import org.example.springbootdeveloper.recommend.respository.UserDetailRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class testapi {
    private final UserDetailRepository userDetailRepository;
    private final UserStyleRepository userStyleRepository;

    public testapi(UserDetailRepository userDetailRepository, UserStyleRepository userStyleRepository) {
        this.userDetailRepository = userDetailRepository;
        this.userStyleRepository = userStyleRepository;
    }

    @GetMapping("/test/userDetail/{id}")
    public ResponseEntity<UserDetail> userDetail(@PathVariable String id){
        Optional<UserDetail> userDetailOptional = userDetailRepository.findByUserId(id);

        if (userDetailOptional.isPresent()) {
            UserDetail user = userDetailOptional.get();
            return ResponseEntity.ok().body(user);
        }

        else{
            return ResponseEntity.notFound().build();

        }

    }


    @GetMapping("/test/userStyle/{id}")
    public ResponseEntity<UserStyle> userStyle(@PathVariable String id){
        Optional<UserStyle> userStyleOptional = userStyleRepository.findByUserId(id);

        if (userStyleOptional.isPresent()) {
            UserStyle user = userStyleOptional.get();
            return ResponseEntity.ok().body(user);
        }

        else{
            return ResponseEntity.notFound().build();

        }

    }
}
