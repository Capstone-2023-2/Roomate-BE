package org.example.springbootdeveloper.recommend.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.animal_type.domain.UserAnimal;
import org.example.springbootdeveloper.animal_type.dto.UserHeartDTO;
import org.example.springbootdeveloper.animal_type.service.HeartService;
import org.example.springbootdeveloper.connection.WebClientServiceImpl;
import org.example.springbootdeveloper.recommend.domain.UserDetail;
import org.example.springbootdeveloper.recommend.domain.UserStar;
import org.example.springbootdeveloper.recommend.dto.UserCardDTO;
import org.example.springbootdeveloper.recommend.respository.UserDetailRepository;
import org.example.springbootdeveloper.recommend.respository.UserStarRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RecommendService {
    private final WebClientServiceImpl webClientServiceImpl;
    private final HeartService heartService;
    private final UserDetailRepository userDetailRepository;
    private final UserStarRepository userStarRepository;
    public Map<String, UserCardDTO> getCards(String id) {
        Map<String, UserCardDTO> userCardDTOMap = new HashMap<>();

        // 여러 사용자에 대한 정보를 가져오는 로직
        for (String userId : getUserValues(id)) {
            UserCardDTO userCardDTO = makeUserCard(userId);
            userCardDTOMap.put(userId, userCardDTO);
        }

        return userCardDTOMap;
    }
    public Map<String, UserCardDTO> getNewCards(String id) {
        Map<String, UserCardDTO> userCardDTOMap = new HashMap<>();

        // 여러 사용자에 대한 정보를 가져오는 로직
        for (String userId : getUserNewValues(id)) {
            UserCardDTO userCardDTO = makeUserCard(userId);
            userCardDTOMap.put(userId, userCardDTO);
        }

        return userCardDTOMap;
    }

    public UserCardDTO makeUserCard(String userId) {
        UserCardDTO userCardDTO = new UserCardDTO();
        UserHeartDTO userHeartDTO = heartService.makeUserHeart(userId);
        Optional<UserDetail> userDetailOptional = userDetailRepository.findByUserId(userId);
        Boolean star;

        String originUserId = SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<UserStar> userStarOptional = userStarRepository.findByUserIdAndStarId(originUserId, userId);

        if (userStarOptional.isPresent()) {
            star = true; //찜하기 해놓은 사람


        } else {
            star = false;
        }

        if (userDetailOptional.isPresent()) {
            UserDetail userDetail = userDetailOptional.get();
            userCardDTO.setNickname(userDetail.getNickname());
            String animal = userDetail.getAnimal();
            if(animal == "polar bear"){
                animal = "polarBear";
            }
            else if(animal == "arctic fox"){
                animal = "arcticFox";
            }
            userCardDTO.setStar(star);
            userCardDTO.setAnimal(animal);
            userCardDTO.setDorm(userDetail.getDorm());
            userCardDTO.setRoom(userDetail.getRoom());
            userCardDTO.setAge(userDetail.getAge());
            userCardDTO.setDept(userDetail.getDept());
            userCardDTO.setStu_num(userDetail.getStu_num());
            userCardDTO.setMbti(userDetail.getMbti());
            userCardDTO.setRhythm(userHeartDTO.getRhythm());
            userCardDTO.setSmoke(userHeartDTO.getSmoke());
            userCardDTO.setNoise(userHeartDTO.getNoise());
            userCardDTO.setTemperature(userHeartDTO.getTemperature());
            userCardDTO.setOutgoing(userHeartDTO.getOutgoing());
            userCardDTO.setClean(userHeartDTO.getClean());
            userCardDTO.setSleep(userHeartDTO.getSleep());
        }

        return userCardDTO;
    }

    private String[] getUserValues(String Id) {
        Map<String, Object> response = webClientServiceImpl.post(Id);

        // Map의 값을 배열로 추출
        String[] userValues = response.values().stream()
                .map(Object::toString)
                .toArray(String[]::new);

        return userValues;
    }

    private String[] getUserNewValues(String Id) {
        Map<String, Object> response = webClientServiceImpl.postUpdate(Id);

        // Map의 값을 배열로 추출
        String[] userValues = response.values().stream()
                .map(Object::toString)
                .toArray(String[]::new);

        return userValues;
    }




}
