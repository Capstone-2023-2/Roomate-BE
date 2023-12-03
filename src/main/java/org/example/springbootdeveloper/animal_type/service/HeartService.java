package org.example.springbootdeveloper.animal_type.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.animal_type.domain.UserAnimal;
import org.example.springbootdeveloper.animal_type.domain.UserStyle;
import org.example.springbootdeveloper.animal_type.dto.UserHeartDTO;
import org.example.springbootdeveloper.animal_type.repository.UserStyleRepository;
import org.example.springbootdeveloper.user.domain.User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HeartService {
    private final UserStyleRepository userStyleRepository;
    public UserStyle loadUserByUsername(String userId){
        return userStyleRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException(userId));
    }


    public UserHeartDTO makeUserHeart(String userId) {

        String rhythm;
        String smoke;
        int noise;
        int temperature;

        int outgoing;
        int clean;
        int sleep;

        UserStyle userStyle = loadUserByUsername(userId);
        if (userStyle.getBedtimeScore() + userStyle.getWakeupScore() > 6) {
            rhythm = "저녁형";
        } else {
            rhythm = "아침형";

        }
        if (userStyle.getCigaretteScore()==1){
            smoke = "흡연자";
        }
        else {
            smoke = "비흡연자";
        }
        int t;
        t = userStyle.getWakeupSensitivity()+ userStyle.getStudySensitivity()+ userStyle.getNotebookSensitivity()+userStyle.getAlarmSensitivity()+userStyle.getLatestudySensitivity()+userStyle.getSnoringSensitivity();
        if (t>15){
            noise =5;
        } else if (t>12) {
            noise=4;
        } else if (t>9) {
            noise=3;

        } else if (t>6) {
            noise=2;
        }
        else{
            noise=1;
        }

        if(userStyle.getInhomeScore()==3){
            outgoing=5;
        } else if (userStyle.getInhomeScore()==2) {
            outgoing=3;
        } else{
            outgoing=1;
        }

        if (userStyle.getCleaningScore() ==3){
            clean = 5;
        } else if (userStyle.getCleaningScore()==2) {
            clean =3;
        }
        else{
            clean=1;
        }

        if(userStyle.getSnoringScore()==1){
            sleep = 5;
        } else if (userStyle.getSnoringScore()==2) {
            sleep = 3;

        }
        else{
            sleep = 1;
        }

        if(userStyle.getColdOrHot()==2){
            temperature= 5;
        } else if (userStyle.getColdOrHot()==3) {
            temperature=4;
        }
        else if (userStyle.getColdOrHot()==1) {
            temperature=4;
        }
        else{
            temperature=1;
        }

        UserHeartDTO userHeartDTO = UserHeartDTO.builder()
                .rhythm(rhythm)
                .smoke(smoke)
                .noise(noise)
                .temperature(temperature)
                .outgoing(outgoing)
                .clean(clean)
                .sleep(sleep)
                .build();
        return userHeartDTO;
    }

}
