package org.example.springbootdeveloper;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebErrorController implements ErrorController {

    @GetMapping({"/","/home", "/error"})
    public String index() {
        return "index.html";
    }


}