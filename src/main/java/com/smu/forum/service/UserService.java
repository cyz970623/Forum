package com.smu.forum.service;

import com.smu.forum.domain.Account;
import com.smu.forum.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final JdbcTemplate jdbcTemplate;

    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User getUser(int accountId) {
        return this.jdbcTemplate.queryForObject(
        "select * from users where account_id = " + accountId,
            new BeanPropertyRowMapper<User>(User.class));
    }

    public void addUser(User user) {
        this.jdbcTemplate.update(
            "insert into users (account_id, nickname) values(" +
            user.getAccountId() + ",'" +
            user.getNickname() + "')");
    }
}
