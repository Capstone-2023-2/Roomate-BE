package org.example.springbootdeveloper.recommend.respository;


import org.example.springbootdeveloper.recommend.domain.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
    Optional<UserDetail> findByUserId(String userId); // UserId로 사용자 정보를 가져옴
}
