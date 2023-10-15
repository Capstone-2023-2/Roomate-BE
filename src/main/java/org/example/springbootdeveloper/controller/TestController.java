package org.example.springbootdeveloper.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    @GetMapping("/test")
    public String userName(){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return userName;
    }


}
