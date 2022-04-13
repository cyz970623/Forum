package com.smu.forum.service;

import com.smu.forum.domain.Question;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    private final JdbcTemplate jdbcTemplate;

    public QuestionService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addQuestion(Question question) {
        this.jdbcTemplate.update("insert into questions (creator_id, title, description, create_time) values(" +
                question.getCreatorId() + ",'" + question.getTitle() + "','" + question.getDescription() + "','" + question.getCreateTime() +
                "')");
    }
}
