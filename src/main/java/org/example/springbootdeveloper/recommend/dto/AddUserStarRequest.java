package org.example.springbootdeveloper.recommend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.springbootdeveloper.recommend.domain.UserDetail;
import org.example.springbootdeveloper.recommend.domain.UserStar;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddUserStarRequest {
    private String userId;
    private String starId;
    public UserStar toEntity(String userId){
        return UserStar.builder()
                .userId(userId)
                .starId(starId)
                .build();
    }
}
