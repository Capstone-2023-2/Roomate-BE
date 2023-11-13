package org.example.springbootdeveloper.recommend.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.animal_type.domain.UserStyle;
import org.example.springbootdeveloper.animal_type.dto.AddUserStyleRequest;
import org.example.springbootdeveloper.recommend.domain.UserDetail;
import org.example.springbootdeveloper.recommend.dto.AddUserDetailRequest;
import org.example.springbootdeveloper.recommend.respository.UserDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DetailService {
    private final UserDetailRepository userDetailRepository;
    public UserDetail save(AddUserDetailRequest request, String userId, char gender){
        return userDetailRepository.save(request.toEntity(userId, gender));
    }

    public List<UserDetail> showTotalList(String userId){
        return userDetailRepository.findAll();
    }

}
