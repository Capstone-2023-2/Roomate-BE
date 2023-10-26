package org.example.springbootdeveloper.user.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.user.domain.User;
import org.example.springbootdeveloper.user.dto.AddUserRequest;
import org.example.springbootdeveloper.user.respository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto) {
        return userRepository.save(User.builder()
                .userId(dto.getUserId())
                .email(dto.getEmail())
                .gender(dto.getGender())
                .nickname(dto.getNickname())
                .status(dto.getStatus())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }

    /* 아이디, 닉네임, 이메일 중복 여부 확인 */
    @Transactional(readOnly = true)
    //@Override
    public boolean checkUserIdDuplication(String userId) {
        boolean userIdDuplicate = userRepository.existsByUserId(userId);
        return userIdDuplicate;
    }

    @Transactional(readOnly = true)
    //@Override
    public boolean checkNicknameDuplication(String nickname) {
        boolean nicknameDuplicate = userRepository.existsByNickname(nickname);
        return nicknameDuplicate;

    }

    @Transactional(readOnly = true)
    //@Override
    public boolean checkEmailDuplication(String email) {
        boolean emailDuplicate = userRepository.existsByEmail(email);
        return emailDuplicate;
    }

}
