package org.example.springbootdeveloper.animal_type.repository;

import org.example.springbootdeveloper.animal_type.domain.UserStyle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserStyleRepository extends JpaRepository<UserStyle, Long> {
    Optional<UserStyle> findByUserId(String userId); // UserId로 사용자 정보를 가져옴

}
