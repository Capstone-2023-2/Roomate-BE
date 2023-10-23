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
    private int bedtimeScore;
    private int wakeupScore;
    private int wakeupSensitivity;
    private int cleaningScore;
    private int cleaningSensitivity;
    private int foodScore;
    private int foodSensitivity;
    private int cigaretteScore;
    private int studyScore;
    private int studySensitivity;
    private int notebookScore;
    private int notebookSensitivity;
    private int alarmScore;
    private int alarmSensitivity;
    private int latestudyScore;
    private int latestudySensitivity;
    private int snoringScore;
    private int snoringSensitivity;
    private int friendlyScore;
    private int inhomeScore;
    private int inhomeSensitivity;
    private int coldOrHot;
    private int summerOrWinter;

    public UserStyle toEntity(String userId){
        return UserStyle.builder()
                .userId(userId)
                .bedtimeScore(bedtimeScore)
                .wakeupScore(wakeupScore)
                .wakeupSensitivity(wakeupSensitivity)
                .cleaningScore(cleaningScore)
                .cleaningSensitivity(cleaningSensitivity)
                .foodScore(foodScore)
                .foodSensitivity(foodSensitivity)
                .cigaretteScore(cigaretteScore)
                .studyScore(studyScore)
                .studySensitivity(studySensitivity)
                .notebookScore(notebookScore)
                .notebookSensitivity(notebookSensitivity)
                .alarmScore(alarmScore)
                .alarmSensitivity(alarmSensitivity)
                .latestudyScore(latestudyScore)
                .latestudySensitivity(latestudySensitivity)
                .snoringScore(snoringScore)
                .snoringSensitivity(snoringSensitivity)
                .friendlyScore(friendlyScore)
                .inhomeScore(inhomeScore)
                .inhomeSensitivity(inhomeSensitivity)
                .coldOrHot(coldOrHot)
                .summerOrWinter(summerOrWinter)

                .build();
    }
}
