package com.smu.forum.controller;

import com.smu.forum.domain.Account;
import com.smu.forum.domain.Question;
import com.smu.forum.domain.User;
import com.smu.forum.service.AccountService;
import com.smu.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class RegisterController {
//    @Autowired
//    private AccountService accountService;
//
//    @Autowired
//    private UserService userService;
//

//    public String register(Model model) {
//        return "register";
//    }
//
//    @GetMapping("/sign_up")
//    public String register(
//            @RequestParam(name="username", required = false) String username,
//            @RequestParam(name="password", required = false) String password,
//            @RequestParam(name="password2", required = false) String password2) {
//
//        if(username.isEmpty()||password.isEmpty()||password2.isEmpty()){
//            return "Username or password is null!";
//            //return success;
//        }
//        if(!password.equals(password2)){
//            return "Passwords are not the same!";
//        }else{
//        Account account = new Account();
//        account.setUsername(username);
//        account.setPassword(password);
//        accountService.addAccount(account);
//
//        account = accountService.getAccount(username);
//        User user = new User();
//        user.setAccountId(Math.toIntExact(account.getId()));
//        user.setNickname(username);
//        userService.addUser(user);
//        return "redirect:login";
//        }
//    }
    @Resource
    private AccountService accountService;

    /**
     * register
     * @param account
     * @return
     */
    @PostMapping("create")
    public Map<String, Object> createAccount(Account account){
        return accountService.createAccount(account);
    }

}
