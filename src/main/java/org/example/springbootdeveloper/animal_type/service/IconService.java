package org.example.springbootdeveloper.animal_type.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.animal_type.domain.UserStyle;
import org.example.springbootdeveloper.animal_type.dto.StyleIconDTO;
import org.example.springbootdeveloper.animal_type.dto.UserHeartDTO;
import org.example.springbootdeveloper.animal_type.repository.UserStyleRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class IconService {
    private final UserStyleRepository userStyleRepository;
    public UserStyle loadUserByUsername(String userId){
        return userStyleRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException(userId));
    }
    public StyleIconDTO makeUserIcon(String userId) {

        String rhythm;
        String smoke;
        String home;
        String hotCold;

        UserStyle userStyle = loadUserByUsername(userId);
        if (userStyle.getBedtimeScore()+ userStyle.getWakeupScore() > 6) {
            rhythm = "저녁형";
        } else {
            rhythm = "아침형";
        }

        // Smoke 설정
        if (userStyle.getCigaretteScore() == 1) {
            smoke = "흡연자";
        } else {
            smoke = "비흡연자";
        }

        // Home 설정
        if (userStyle.getInhomeScore() == 1) {
            home = "집순이";
        } else if (userStyle.getInhomeScore() == 2) {
            home = "집순이";
        } else{
            home = "외출형";
        }

        // Hot/Cold 설정
        if (userStyle.getColdOrHot()== 1) {
            hotCold = "추위에 약함";
        } else if (userStyle.getColdOrHot() == 3) {
            hotCold = "더위에 약함";
        } else{
            if (userStyle.getSummerOrWinter()== 1) {
                hotCold = "추위에 약함";
            } else {
                hotCold = "더위에 약함";
            }
        }

        StyleIconDTO styleIconDTO = StyleIconDTO.builder()
                .rhythm(rhythm)
                .smoke(smoke)
                .home(home)
                .hotCold(hotCold)
                .build();
        return styleIconDTO;
    }


}
