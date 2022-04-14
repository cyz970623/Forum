package com.smu.forum.service;

import com.smu.forum.domain.Account;
import com.smu.forum.domain.Answer;
import com.smu.forum.domain.Question;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final JdbcTemplate jdbcTemplate;

    public AccountService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addAccount(Account account) {
        this.jdbcTemplate.update(
            "insert into accounts (username, password) values('" +
                account.getUsername() + "','" +
                account.getPassword() + "')");
    }

    public Account getAccount(String username) {
        return this.jdbcTemplate.queryForObject(
        "select password from accounts where username = '" + username + "'",
            new BeanPropertyRowMapper<Account>(Account.class));
    }
}
