package org.example.springbootdeveloper.animal_type.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.animal_type.domain.UserStyle;
import org.example.springbootdeveloper.animal_type.repository.UserStyleRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TextStyleService {
    private final UserStyleRepository userStyleRepository;
    public UserStyle loadUserByUsername(String userId){
        return userStyleRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException(userId));
    }

    public String make_lifestyle_text(String userId) {
        UserStyle userStyle = loadUserByUsername(userId);

        int bedtimeScore = userStyle.getBedtimeScore();
        int wakeupScore = userStyle.getWakeupScore();
        int cleaningScore = userStyle.getCleaningScore();
        int foodScore = userStyle.getFoodScore();
        int cigaretteScore = userStyle.getCigaretteScore();
        int studyScore = userStyle.getStudyScore();
        int notebookScore = userStyle.getNotebookScore();
        int friendlyScore = userStyle.getFriendlyScore();
        int snoringScore = userStyle.getSnoringScore();


        String a = "";
        String b = "";
        String c = "";
        String d = "";
        String e = "";
        String f = "";
        String g = "";
        String h = "";
        String i = "";

        if (bedtimeScore == 1) {
            a = "밤 11시 이전";
        } else if (bedtimeScore == 2) {
            a = "밤 12시 쯤";
        } else if (bedtimeScore == 3) {
            a = "새벽 1시 쯤";
        } else if (bedtimeScore == 4) {
            a = "새벽 2시 쯤";
        } else if (bedtimeScore == 5) {
            a = "새벽 3시 이후";
        }

        if (wakeupScore == 1) {
            b = "오전 5시 이전";
        } else if (wakeupScore == 2) {
            b = "오전 6~7시 쯤";
        } else if (wakeupScore == 3) {
            b = "오전 8~9시 쯤";
        } else if (wakeupScore == 4) {
            b = "오전 10~11시 쯤";
        } else if (wakeupScore == 5) {
            b = "낮 12시 이후";
        }

        if (cleaningScore == 1) {
            c = "청소는 거의 하지 않는";
        } else if (cleaningScore == 2) {
            c = "쓰레기가 적당히 쌓이면 버리는";
        } else if (cleaningScore == 3) {
            c = "매일매일 깨끗하게 청소하는";
        }

        if (foodScore == 1) {
            d = "주로 방에서 먹으며";
        } else if (foodScore == 2) {
            d = "가끔 방에서 먹으며";
        } else if (foodScore == 3) {
            d = "방에서는 잘 먹지 않으며";
        }

        if (cigaretteScore == 1) {
            e = "흡연자";
        } else if (cigaretteScore == 3) {
            e = "비흡연자";
        }

        if (studyScore == 1) {
            f = "주로 방";
        } else if (studyScore == 2) {
            f = "방이나 도서관";
        } else if (studyScore == 3) {
            f = "주로 도서관";
        }

        if (notebookScore == 1) {
            g = "자주 사용해요";
        } else if (notebookScore == 2) {
            g = "가끔 사용해요";
        } else if (notebookScore == 3) {
            g = "거의 사용하지 않아요";
        }

        if (friendlyScore == 1) {
            h = "친하게";
        } else if (friendlyScore == 3) {
            h = "적당한 거리를 두고";
        }

        if (snoringScore == 1) {
            i = "심한";
        } else if (snoringScore == 2) {
            i = "조금 있는";
        } else if (snoringScore == 3) {
            i = "거의 없는";
        }

        return String.format("%s에 취침하고, %s에 일어나요. %s 편이에요. 음식은 %s, %s에요. %s에서 공부하고, 노트북을 %s. 룸메이트와 %s 지내고 싶어요. 잠버릇은 %s 편이에요.",
                a, b, c, d, e, f, g, h, i);
    }
}
