package org.example.springbootdeveloper.recommend.respository;


import org.example.springbootdeveloper.recommend.domain.UserStar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserStarRepository extends JpaRepository<UserStar, Long> {
    Optional<UserStar> findByUserIdAndStarId(String userId, String starId);
    Optional<List<UserStar>> findByUserId(String userId); // 특정 사용자가 찜한 목록을 가져옴
}
