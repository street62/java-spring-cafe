package com.kakao.cafe.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.kakao.cafe.domain.users.User;
import com.kakao.cafe.domain.users.UserRepository;

@Controller
public class UserController {

    private final UserRepository userRepository = new UserRepository(new ArrayList<>());
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/users")
    public void showUsers() {

    }

    @PostMapping("/users")
    public String addUser(User user) {
        logger.info("user name = {}", user.getName());
        userRepository.save(user);
        return "redirect:/users";
    }
}
