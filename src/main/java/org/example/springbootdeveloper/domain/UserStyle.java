package org.example.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name= "userstyles")
public class UserStyle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "userId")
    private String userId;

    private String userAnimal;
    private int bedtimeScore;
    private int wakeupScore;

    @Builder
    public UserStyle(String userId, String userAnimal, int bedtimeScore, int wakeupScore){
        this.userId = userId;
        this.userAnimal = userAnimal;
        this.bedtimeScore = bedtimeScore;
        this.wakeupScore = wakeupScore;
    }

}
