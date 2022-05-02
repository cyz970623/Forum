package com.smu.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class SystemController {

//    @GetMapping("home")
//    public String home(){
//        return "home";
//    }

    @GetMapping("register")
    public String register(){
        return "register";
    }

    @GetMapping("login")
    public String login(){
        return "login";
    }


}
