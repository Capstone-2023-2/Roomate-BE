package org.example.springbootdeveloper.recommend.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.animal_type.domain.UserAnimal;
import org.example.springbootdeveloper.recommend.domain.UserDetail;
import org.example.springbootdeveloper.recommend.domain.UserStar;
import org.example.springbootdeveloper.recommend.dto.AddUserDetailRequest;
import org.example.springbootdeveloper.recommend.dto.AddUserStarRequest;
import org.example.springbootdeveloper.recommend.respository.UserDetailRepository;
import org.example.springbootdeveloper.recommend.respository.UserStarRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StarService {
    private final UserStarRepository userStarRepository;
    public UserStar save(String starId, String userId){
        UserStar userStar = UserStar.builder()
                .userId(userId)
                .starId(starId)
                .build();
        return userStarRepository.save(userStar);
    }

    public boolean delete(String starId, String userId) {
        Optional<UserStar> userStarOptional = userStarRepository.findByUserIdAndStarId(userId, starId);

        if (userStarOptional.isPresent()) {
            UserStar userStar = userStarOptional.get();
            userStarRepository.delete(userStar);
            return true; // Deletion successful
        } else {
            return false; // UserStar not found
        }
    }


}