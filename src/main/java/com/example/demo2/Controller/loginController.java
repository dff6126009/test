package com.example.demo2.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class loginController {
    @RequestMapping("/")
    public String login(){
        return "all_login";
    }

}
