package com.smu.forum.controller;

import com.smu.forum.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class MailController {

    @Resource
    private AccountService accountService;

    @GetMapping("activation")
    public Map<String, Object> activationAccount(String confirmCode){
        return accountService.activationAccount(confirmCode);
    }
}
