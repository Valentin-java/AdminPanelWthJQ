package com.vjava.transfer1.controller;


import com.vjava.transfer1.model.User;
import com.vjava.transfer1.service.UserService;
import com.vjava.transfer1.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("people", users);
        return "index";
    }

    @GetMapping("/{id}/edit")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("person", userService.findById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") User user, @PathVariable("id") Long id) {
        userService.update(user, id);
        return "redirect: index";
    }

    @GetMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect: index";
    }

    @GetMapping("/new")
    public String addForm(Model model) {
        model.addAttribute("person", new User());
        return "new";
    }

    @PostMapping()
    public String add(@ModelAttribute("person") User user) {
        userService.saveUser(user);
        return "redirect: index";
    }

    @GetMapping("page")
    public String userPage(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "page";
    }



}
