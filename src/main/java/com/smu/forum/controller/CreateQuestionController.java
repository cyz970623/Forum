package com.smu.forum.controller;

import com.smu.forum.domain.Question;
import com.smu.forum.service.HomeService;
import com.smu.forum.service.QuestionService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class CreateQuestionController {
    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/create_question")
    public String createQuestion(Model model) {
        return "create_question";
    }

    @GetMapping("/submit_question")
    public String submit(
            @RequestParam(name="title", required = false) String title,
            @RequestParam(name="description", required = false) String description) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Question question = new Question();
        question.setCreatorId(1);//TODO
        question.setTitle(title);
        question.setDescription(description);
        question.setCreateTime(dateFormat.format(date));
        questionService.addQuestion(question);
        return "redirect:home";
    }
}
