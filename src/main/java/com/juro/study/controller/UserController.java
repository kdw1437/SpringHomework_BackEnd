package com.juro.study.controller;

import com.juro.study.model.User;
import com.juro.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String author, @RequestParam String password, HttpSession session, Model model) {
        User user = userService.getUserByAuthor(author);
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("loggedInUser", author);
            return "redirect:/posts";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String author, @RequestParam String password, Model model) {
        User existingUser = userService.getUserByAuthor(author);
        if (existingUser != null) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }
        userService.createUser(author, password);
        return "redirect:/user/login";
    }

    @GetMapping("/changePassword")
    public String changePasswordForm() {
        return "changePassword";
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestParam String author, @RequestParam String oldPassword, 
                                 @RequestParam String newPassword, Model model) {
        boolean changed = userService.changePassword(author, oldPassword, newPassword);
        if (changed) {
            return "redirect:/user/login";
        } else {
            model.addAttribute("error", "Failed to change password");
            return "changePassword";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loggedInUser");
        return "redirect:/user/login";
    }
}