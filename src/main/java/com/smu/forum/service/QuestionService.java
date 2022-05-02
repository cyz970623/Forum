package com.smu.forum.service;

import com.smu.forum.domain.Question;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuestionService {
    private final JdbcTemplate jdbcTemplate;

    public QuestionService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addQuestion(Question question) {
        this.jdbcTemplate.update(
        "insert into questions (creator_id, title, description, create_time) values(" +
            question.getCreatorId() + ",'" +
            question.getTitle() + "','" +
            question.getDescription() + "','" +
            question.getCreateTime() + "')"
        );
    }

    public Question getQuestion(int id) {
        return this.jdbcTemplate.queryForObject(
        "select * from questions where id = '" + id +
             "'", new BeanPropertyRowMapper<Question>(Question.class));
    }

    public void updateViewCount(Question question) {
        this.jdbcTemplate.update(
        "update questions set view_count = " +
            (question.getViewCount() + 1) +
            " where id = " + question.getId());
    }

    public void updateAnswerCount(Question question) {
        this.jdbcTemplate.update(
        "update questions set answer_count = " +
            (question.getAnswerCount() + 1) +
            " where id = " + question.getId());
    }

    public void updateLikeCount(Question question) {
        this.jdbcTemplate.update(
        "update questions set like_count = " +
            (question.getLikeCount() + 1) +
            " where id = " + question.getId());
    }
}
