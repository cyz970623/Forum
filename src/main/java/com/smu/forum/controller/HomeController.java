package com.smu.forum.controller;

import com.smu.forum.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    private HomeService homeService;

    @RequestMapping("/home")
    public String home(Model model) {
        List<Map<String, Object>> questions = homeService.getQuestions();
        model.addAttribute("questions", questions);
        return "home";
    }
}
