package com.smu.forum.service;

import com.smu.forum.domain.Account;
import com.smu.forum.domain.Answer;
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

    public void updateNickname(User user, String nickname) {
        this.jdbcTemplate.update(
        "update users set nickname = '" +
            nickname + "' where account_id = " + user.getAccountId());
    }

    public void updateProfilePhotoUrl(User user, String url) {
        this.jdbcTemplate.update(
        "update users set url = '" +
            url + "' where account_id = " + user.getAccountId());
    }

    public String getProfileUrl(int accountId) {
        return this.jdbcTemplate.queryForObject(
        "select * from users where account_id = " + accountId,
            new BeanPropertyRowMapper<User>(User.class)).getUrl();
    }
}
