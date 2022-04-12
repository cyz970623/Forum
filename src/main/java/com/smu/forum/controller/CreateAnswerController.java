package com.smu.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CreateAnswerController {
    @RequestMapping("/create_answer")
    public String register(Model model) {
        return "create_answer";
    }
}
