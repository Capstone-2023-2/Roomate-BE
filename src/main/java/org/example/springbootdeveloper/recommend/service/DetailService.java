package org.example.springbootdeveloper.recommend.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.animal_type.domain.UserStyle;
import org.example.springbootdeveloper.animal_type.dto.AddUserStyleRequest;
import org.example.springbootdeveloper.recommend.domain.UserDetail;
import org.example.springbootdeveloper.recommend.dto.AddUserDetailRequest;
import org.example.springbootdeveloper.recommend.respository.UserDetailRepository;
import org.example.springbootdeveloper.user.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DetailService {
    private final UserDetailRepository userDetailRepository;
    public UserDetail save(AddUserDetailRequest request, String userId, char gender, boolean sensitive, String animal, String nickname){
        return userDetailRepository.save(request.toEntity(userId, gender, sensitive, animal, nickname));
    }

    public List<UserDetail> showTotalList(String userId){
        return userDetailRepository.findAll();
    }

    public List<UserDetail> showFilteredUserList(String userId) {
        // userId에 해당하는 UserDetail을 가져옴
        Optional<UserDetail> userDetailOptional = userDetailRepository.findByUserId(userId);
        if (userDetailOptional.isPresent()) {
            UserDetail userDetail = userDetailOptional.get();
            if (userDetail != null) {
                // dorm, room, gender가 현재 사용자와 일치하는 사용자들을 가져옴
                return userDetailRepository.findByDormAndRoomAndGender(
                        userDetail.getDorm(),
                        userDetail.getRoom(),
                        userDetail.getGender()
                );
            } else {
                // 현재 사용자가 존재하지 않으면 빈 목록 반환
                return List.of();
            }
        } else {

            return List.of();
        }
    }


}
