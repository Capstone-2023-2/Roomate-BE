package org.example.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name= "user_styles")
public class UserStyle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "userId")
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


    @Builder
    public UserStyle(String userId, int bedtimeScore, int wakeupScore,int wakeupSensitivity, int cleaningScore, int cleaningSensitivity, int foodScore, int foodSensitivity, int cigaretteScore, int studyScore, int studySensitivity, int notebookScore, int notebookSensitivity, int alarmScore, int alarmSensitivity, int latestudyScore, int latestudySensitivity, int snoringScore, int snoringSensitivity, int friendlyScore, int inhomeScore, int inhomeSensitivity, int coldOrHot, int summerOrWinter) {
        this.userId = userId;
        this.bedtimeScore = bedtimeScore;
        this.wakeupScore = wakeupScore;
        this.wakeupSensitivity = wakeupSensitivity;
        this.cleaningScore = cleaningScore;
        this.cleaningSensitivity = cleaningSensitivity;
        this.foodScore = foodScore;
        this.foodSensitivity = foodSensitivity;
        this.cigaretteScore = cigaretteScore;
        this.studyScore = studyScore;
        this.studySensitivity = studySensitivity;
        this.notebookScore = notebookScore;
        this.notebookSensitivity = notebookSensitivity;
        this.alarmScore = alarmScore;
        this.alarmSensitivity = alarmSensitivity;
        this.latestudyScore = latestudyScore;
        this.latestudySensitivity = latestudySensitivity;
        this.snoringScore = snoringScore;
        this.snoringSensitivity = snoringSensitivity;
        this.friendlyScore = friendlyScore;
        this.inhomeScore = inhomeScore;
        this.inhomeSensitivity = inhomeSensitivity;
        this.coldOrHot = coldOrHot;
        this.summerOrWinter = summerOrWinter;
    }

}
