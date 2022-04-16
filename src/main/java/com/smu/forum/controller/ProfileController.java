package com.smu.forum.controller;

import com.smu.forum.Util.GoogleUpdateFileUtil;
import com.smu.forum.domain.Property;
import com.smu.forum.domain.User;
import com.smu.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Controller
public class ProfileController {
    @Autowired
    private UserService userService;

    @Autowired
    private Property property;

    @RequestMapping("/profile")
    public String profile(Model model) {
        User user = userService.getUser(property.getId());
        String url = userService.getProfileUrl(property.getId());
        if (url == null) {
            url = "https://storage.cloud.google.com/forum-project/default.jpeg";
        }
        model.addAttribute("url", url);
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/change_nickname")
    public String changeNickname(@RequestParam(name="nickname", required = false) String nickname) {
        User user = userService.getUser(property.getId());
        userService.updateNickname(user, nickname);
        return "redirect:profile";
    }

    @PostMapping("/upload_profile")
    public String uploadProfile(@RequestParam(name="file", required = false) MultipartFile multipartfile) throws IOException {
        String url = GoogleUpdateFileUtil.uploadFile(multipartfile, "user" + String.valueOf(property.getId()));
        User user = userService.getUser(property.getId());
        userService.updateProfilePhotoUrl(user, url);
        return "redirect:profile";
    }


}
