package com.smu.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QuestionDetailController {
    @RequestMapping("/question_detail")
    public String register(Model model) {
        return "question_detail";
    }
}
