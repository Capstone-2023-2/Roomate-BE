package org.example.springbootdeveloper.animal_type.domain;

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
    @Column(name = "user_id")
    private String userId;

    @Column(name = "bedtime_score")
    private int bedtimeScore;

    @Column(name = "wakeup_score")
    private int wakeupScore;

    @Column(name = "wakeup_sensitivity")
    private int wakeupSensitivity;

    @Column(name = "cleaning_score")
    private int cleaningScore;

    @Column(name = "cleaning_sensitivity")
    private int cleaningSensitivity;

    @Column(name = "food_score")
    private int foodScore;

    @Column(name = "food_sensitivity")
    private int foodSensitivity;

    @Column(name = "cigarette_score")
    private int cigaretteScore;

    @Column(name = "study_score")
    private int studyScore;

    @Column(name = "study_sensitivity")
    private int studySensitivity;

    @Column(name = "notebook_score")
    private int notebookScore;

    @Column(name = "notebook_sensitivity")
    private int notebookSensitivity;

    @Column(name = "alarm_score")
    private int alarmScore;

    @Column(name = "alarm_sensitivity")
    private int alarmSensitivity;

    @Column(name = "latestudy_score")
    private int latestudyScore;

    @Column(name = "latestudy_sensitivity")
    private int latestudySensitivity;

    @Column(name = "snoring_score")
    private int snoringScore;

    @Column(name = "snoring_sensitivity")
    private int snoringSensitivity;

    @Column(name = "friendly_score")
    private int friendlyScore;

    @Column(name = "inhome_score")
    private int inhomeScore;

    @Column(name = "inhome_sensitivity")
    private int inhomeSensitivity;

    @Column(name = "cold_or_hot")
    private int coldOrHot;

    @Column(name = "summer_or_winter")
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
