package com.smu.forum.controller;

import com.google.api.services.sqladmin.SQLAdmin;
import com.smu.forum.domain.Account;
import com.smu.forum.domain.Property;
import com.smu.forum.domain.Question;
import com.smu.forum.domain.User;
import com.smu.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {
    @Autowired
    private UserService userService;

    @Autowired
    private Property property;

    @RequestMapping("/profile")
    public String profile(Model model) {
        User user = userService.getUser(property.getId());
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/change_nickname")
    public String changeNickname(@RequestParam(name="nickname", required = false) String nickname) {
        User user = userService.getUser(property.getId());
        userService.updateNickname(user, nickname);
        return "redirect:profile";
    }
}
