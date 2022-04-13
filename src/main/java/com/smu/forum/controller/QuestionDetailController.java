package com.smu.forum.controller;

import com.smu.forum.domain.Answer;
import com.smu.forum.domain.Question;
import com.smu.forum.service.AnswerService;
import com.smu.forum.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class QuestionDetailController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @RequestMapping("/question_detail/{id}")
    public String register(@PathVariable(value = "id") String id, Model model) {
        List<Map<String, Object>> answers = answerService.getAnswers(Integer.parseInt(id));
        Question question = questionService.getQuestion(Integer.parseInt(id));
        model.addAttribute("answers", answers);
        model.addAttribute("question", question);
        return "question_detail";
    }

    @GetMapping("/submit_answer")
    public String submit(
            @RequestParam(name="questionId", required = false) int questionId,
            @RequestParam(name="description", required = false) String description) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");

        Answer answer = new Answer();
        answer.setQuestionId(questionId);
        answer.setAnswererId(1);//TODO
        answer.setDescription(description);
        answer.setAnswerTime(dateFormat.format(date));
        answerService.addAnswer(answer);
        return "redirect:question_detail/" + questionId;
    }
}
