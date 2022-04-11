package com.smu.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/home")
    public String hello() {
        return "home";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        return "index";
    }
}
