package com.smu.forum.controller;

import com.smu.forum.domain.Account;
import com.smu.forum.domain.Property;
import com.smu.forum.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private Property property;

    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/sign_in")
    public String login(
            @RequestParam(name="username", required = false) String username,
            @RequestParam(name="password", required = false) String password) {

        Account account = new Account();
        account = accountService.getAccount(username);
        if (account.getPassword().equals(password)) {
            property.setId(Math.toIntExact(account.getId()));
            return "redirect:home";
        } else {
            return "login";
        }
    }
}
