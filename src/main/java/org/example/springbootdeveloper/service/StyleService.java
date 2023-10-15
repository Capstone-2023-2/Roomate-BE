package org.example.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.domain.UserStyle;
import org.example.springbootdeveloper.dto.AddUserStyleRequest;
import org.example.springbootdeveloper.repository.UserStyleRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StyleService {
    private final UserStyleRepository userStyleRepository;
    public UserStyle save(AddUserStyleRequest request, String userId){
        return userStyleRepository.save(request.toEntity(userId));
    }
}
