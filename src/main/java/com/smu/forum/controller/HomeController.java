package com.smu.forum.controller;

import com.smu.forum.domain.Answer;
import com.smu.forum.domain.Question;
import com.smu.forum.service.HomeService;
import com.smu.forum.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    private HomeService homeService;

    @Autowired
    private QuestionService questionService;

    @RequestMapping("/home")
    public String home(Model model) {
        List<Map<String, Object>> questions = homeService.getQuestions();
        model.addAttribute("questions", questions);
        return "home";
    }

    @GetMapping("/like_question")
    public String like(@RequestParam(name="questionId", required = false) int questionId) {
        Question question = questionService.getQuestion(questionId);
        questionService.updateLikeCount(question);
        return "redirect:home";
    }
}
