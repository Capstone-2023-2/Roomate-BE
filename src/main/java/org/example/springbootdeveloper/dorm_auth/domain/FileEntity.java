package org.example.springbootdeveloper.dorm_auth.domain;

import lombok.*;

import jakarta.persistence.*;
import java.util.Date;

@Getter // getter 메소드 생성
@Builder // 빌더를 사용할 수 있게 함
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="file") // 테이블 명을 작성
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pid;

    @Column(nullable = false, unique = true, length = 1000)
    private String filename;

    @Column(nullable = false, updatable = false)
    private String userId;

    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private String created_dt;
}

