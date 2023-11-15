package org.example.springbootdeveloper.connection;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class WebClientServiceImpl {

    public String get() {
        String code = "myCode";

        // webClient 기본 설정
        WebClient webClient =
                WebClient
                        .builder()
                        .baseUrl("https://874f-219-255-207-60.ngrok-free.app")
                        .build();

        // api 요청
        String response =
                webClient
                        .get()
                        .uri("/hello")
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();

        // 결과 확인
        log.info(response);
        return response;
    }
}
