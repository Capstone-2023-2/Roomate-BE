package org.example.springbootdeveloper.recommend.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.animal_type.domain.UserAnimal;
import org.example.springbootdeveloper.animal_type.dto.UserHeartDTO;
import org.example.springbootdeveloper.animal_type.service.HeartService;
import org.example.springbootdeveloper.connection.WebClientServiceImpl;
import org.example.springbootdeveloper.recommend.domain.UserDetail;
import org.example.springbootdeveloper.recommend.dto.UserCardDTO;
import org.example.springbootdeveloper.recommend.respository.UserDetailRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RecommendService {
    private final WebClientServiceImpl webClientServiceImpl;
    private final HeartService heartService;
    private final UserDetailRepository userDetailRepository;
    public Map<String, UserCardDTO> getCards(String id) {
        Map<String, UserCardDTO> userCardDTOMap = new HashMap<>();

        // 여러 사용자에 대한 정보를 가져오는 로직
        for (int userId : getUserValues(id)) {
            String userIdString = String.valueOf(userId);
            UserCardDTO userCardDTO = makeUserCard(userIdString);
            userCardDTOMap.put(userIdString, userCardDTO);
        }

        return userCardDTOMap;
    }

    public Map<String, UserCardDTO> getNewCards(String id) {
        Map<String, UserCardDTO> userCardDTOMap = new HashMap<>();

        // 여러 사용자에 대한 정보를 가져오는 로직
        for (int userId : getUserValues(id)) {
            String userIdString = String.valueOf(userId);
            UserCardDTO userCardDTO = makeUserCard(userIdString);
            userCardDTOMap.put(userIdString, userCardDTO);
        }

        return userCardDTOMap;
    }

    private UserCardDTO makeUserCard(String userId) {
        UserCardDTO userCardDTO = new UserCardDTO();
        UserHeartDTO userHeartDTO = heartService.makeUserHeart(userId);
        Optional<UserDetail> userDetailOptional = userDetailRepository.findByUserId(userId);

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

    private int[] getUserValues(String Id) {
        Map<String, Object> response = webClientServiceImpl.post(Id);

        // Map의 값을 배열로 추출
        int[] userValues = response.values().stream()
                .mapToInt(value -> Integer.parseInt(value.toString()))
                .toArray();

        return userValues;
    }

    private int[] getUserNewValues(String Id) {
        Map<String, Object> response = webClientServiceImpl.postUpdate(Id);

        // Map의 값을 배열로 추출
        int[] userValues = response.values().stream()
                .mapToInt(value -> Integer.parseInt(value.toString()))
                .toArray();

        return userValues;
    }

}
