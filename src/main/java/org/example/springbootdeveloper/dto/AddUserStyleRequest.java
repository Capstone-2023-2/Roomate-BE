package org.example.springbootdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.springbootdeveloper.domain.UserStyle;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddUserStyleRequest {
    private String userId;
    private String userAnimal;
    private int bedtimeScore;
    private int wakeupScore;

    public UserStyle toEntity(String userId){
        return UserStyle.builder()
                .userId(userId)
                .userAnimal(userAnimal)
                .bedtimeScore(bedtimeScore)
                .wakeupScore(wakeupScore)
                .build();
    }
}
