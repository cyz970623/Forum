package com.smu.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("username", "Mike");
        model.addAttribute("password", "123456");
        return "login";
    }
}
