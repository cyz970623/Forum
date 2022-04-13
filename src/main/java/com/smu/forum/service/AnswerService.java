package com.smu.forum.service;

import com.smu.forum.domain.Answer;
import com.smu.forum.domain.Question;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AnswerService {
    private final JdbcTemplate jdbcTemplate;

    public AnswerService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addAnswer(Answer answer) {
        this.jdbcTemplate.update(
                "insert into answers (question_id, answerer_id, description, answer_time) values(" +
                        answer.getQuestionId() + "," +
                        answer.getAnswererId() + ",'" +
                        answer.getDescription() + "','" +
                        answer.getAnswerTime() + "')");
    }

    public List<Map<String, Object>> getAnswers(int id) {
        return this.jdbcTemplate.queryForList("select * from answers where question_id = " + id + " order by id desc");
    }
}
