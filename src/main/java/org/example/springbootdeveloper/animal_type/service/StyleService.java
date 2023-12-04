package org.example.springbootdeveloper.animal_type.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.animal_type.domain.UserStyle;
import org.example.springbootdeveloper.animal_type.dto.AddUserStyleRequest;
import org.example.springbootdeveloper.animal_type.repository.UserStyleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StyleService {
    private final UserStyleRepository userStyleRepository;
    public UserStyle save(AddUserStyleRequest request, String userId){
        return userStyleRepository.save(request.toEntity(userId));
    }

    public Boolean doesUserStyleExist(String userId) {
        // userStyleRepository에서 userId를 가진 사용자 스타일을 찾기
        Optional<UserStyle> userStyleOptional = userStyleRepository.findByUserId(userId);

        // 사용자 스타일이 존재하는지 여부를 반환
        return userStyleOptional.isPresent();
    }
}
