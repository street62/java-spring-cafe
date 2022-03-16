package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ExceptionHandler(IllegalStateException.class)
    public Object illegalStateHandler(Exception e) {
        System.err.println(e.getMessage());
        return "redirect:/users";
    }

    @GetMapping
    public String viewList(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "../templates/list";
    }

    @GetMapping("/create")
    public String createForm() {
        return "/user/form";
    }

    @PostMapping("/create")
    public String create(UserForm form) {
        User user = new User(form.getUserId(), form.getName(), form.getEmail());
        userService.join(user);
        return "redirect:/users";
    }

    @GetMapping("/{userId}")
    public String viewUserInfo(@PathVariable("userId") String userId, Model model) {
        User foundUser = userService.findById(userId);
        model.addAttribute("user", foundUser);
        return "../templates/profile";
    }
}
