package com.smu.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CreateQuestionController {
    @RequestMapping("/create_question")
    public String register(Model model) {
        return "create_question";
    }
}
