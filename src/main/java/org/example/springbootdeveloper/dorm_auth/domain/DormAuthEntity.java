package org.example.springbootdeveloper.dorm_auth.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter // getter 메소드 생성
@Builder // 빌더를 사용할 수 있게 함
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="dorm_auth") // 테이블 명을 작성
public class DormAuthEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pid;

    @Column(name = "user_id",nullable = false, updatable = false)
    private String userId;

    @Column(nullable = false)
    private boolean status;
}
