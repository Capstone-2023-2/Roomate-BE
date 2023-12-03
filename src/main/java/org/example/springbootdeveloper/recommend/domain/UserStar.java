package org.example.springbootdeveloper.recommend.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name= "user_stars")
public class UserStar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "star_id", nullable = false)
    private String starId; //찜한 상대 id




    @Builder
    public UserStar(String userId, String starId)
    {
        this.userId = userId;
        this.starId = starId;
    }
}
