package org.example.springbootdeveloper.dorm_auth.repository;

import org.example.springbootdeveloper.animal_type.domain.UserAnimal;
import org.example.springbootdeveloper.dorm_auth.domain.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {
    Optional<FileEntity> findByUserId(String userId); // UserId로 사용자 정보를 가져옴

}