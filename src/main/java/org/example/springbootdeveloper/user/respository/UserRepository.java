package org.example.springbootdeveloper.user.respository;

import org.example.springbootdeveloper.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId); // UserId로 사용자 정보를 가져옴

    Optional<User> findByNickname(String nickname); // UserId로 사용자 정보를 가져옴
    boolean existsByUserId(String userId);
    boolean existsByNickname(String nickname);
    boolean existsByEmail(String email);

}
