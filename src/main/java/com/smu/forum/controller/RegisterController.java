package com.smu.forum.controller;

import com.smu.forum.domain.Account;
import com.smu.forum.domain.Question;
import com.smu.forum.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class RegisterController {
    @Autowired
    private AccountService accountService;

    @RequestMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @GetMapping("/sign_up")
    public String register(
            @RequestParam(name="username", required = false) String username,
            @RequestParam(name="password", required = false) String password) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        accountService.addAccount(account);
        return "redirect:login";
    }
}
