package org.example.springbootdeveloper.recommend.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.animal_type.domain.UserAnimal;
import org.example.springbootdeveloper.recommend.domain.UserDetail;
import org.example.springbootdeveloper.recommend.domain.UserStar;
import org.example.springbootdeveloper.recommend.dto.AddUserDetailRequest;
import org.example.springbootdeveloper.recommend.dto.AddUserStarRequest;
import org.example.springbootdeveloper.recommend.dto.UserCardDTO;
import org.example.springbootdeveloper.recommend.respository.UserDetailRepository;
import org.example.springbootdeveloper.recommend.respository.UserStarRepository;
import org.example.springbootdeveloper.user.domain.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StarService {
    private final UserStarRepository userStarRepository;
    private final UserDetailRepository userDetailRepository;
    private final RecommendService recommendService;
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


    public List<UserCardDTO> showStarList(String userId) {
        Optional<List<UserStar>> userOptional = userStarRepository.findByUserId(userId);
        UserCardDTO userCardDTO = new UserCardDTO();
        List<UserCardDTO> userCardDTOs = new ArrayList<>();

        if (userOptional.isPresent()) {
            List<UserStar> userStars = userOptional.get();

            for (UserStar userStar : userStars) {
                String starId = userStar.getStarId();

                // 현재 starId에 대한 UserDetail을 검색합니다.
                Optional<UserDetail> userDetailOptional = userDetailRepository.findByUserId(starId);

                // userDetailOptional이 존재하는 경우에만 정보를 결과 목록에 추가합니다.
                if (userDetailOptional.isPresent()) {
                    UserDetail userDetail = userDetailOptional.get();
                    userCardDTO = recommendService.makeUserCard(userId);
                    userCardDTOs.add(userCardDTO);
                }
            }

        }
        return  userCardDTOs;


    }

}