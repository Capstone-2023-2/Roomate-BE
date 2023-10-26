package org.example.springbootdeveloper.animal_type.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.animal_type.domain.UserStyle;
import org.example.springbootdeveloper.animal_type.dto.AddUserStyleRequest;
import org.example.springbootdeveloper.animal_type.repository.UserStyleRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StyleService {
    private final UserStyleRepository userStyleRepository;
    public UserStyle save(AddUserStyleRequest request, String userId){
        return userStyleRepository.save(request.toEntity(userId));
    }
}
