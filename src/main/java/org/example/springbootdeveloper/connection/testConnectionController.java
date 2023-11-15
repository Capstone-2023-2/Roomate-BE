package org.example.springbootdeveloper.connection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testConnectionController {
    private final WebClientServiceImpl webClientServiceImpl;

    public testConnectionController(WebClientServiceImpl webClientServiceImpl) {
        this.webClientServiceImpl = webClientServiceImpl;
    }

    @GetMapping("/api/react/hello")
    public String test() {
        String a = webClientServiceImpl.get();
        return "Hello, world!" + a;
    }
}

