package org.example.springbootdeveloper.animal_type.repository;

import org.example.springbootdeveloper.animal_type.domain.UserAnimal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAnimalRepository extends JpaRepository<UserAnimal, Long> {
    Optional<UserAnimal> findByUserId(String userId); // UserId로 사용자 정보를 가져옴
}
