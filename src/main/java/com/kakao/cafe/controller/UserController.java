package com.kakao.cafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.service.UserService;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/form")
    public String createForm() {
        return "user/form";
    }

    @PostMapping("/user/form")
    public String create(User user) {

        userService.join(user);
        return "redirect:/";
    }

    @GetMapping("/create")
    public String create() {
        return "create";
    }
}
