package com.vjava.transfer1.controller;


import com.vjava.transfer1.model.User;
import com.vjava.transfer1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("admin/")
    public String showAll(Model model, Principal principal) {
        User mUser = userService.findByUsername(principal.getName());
        model.addAttribute("mUser", mUser);
        return "admin/index";
    }

    @GetMapping("admin/findAll")
    @ResponseBody
    public List<User> fiandAll() {
        return userService.findAll();
    }

    @GetMapping("admin/findOne/{id}")
    @ResponseBody
    public User findOne(@PathVariable("id") Long id) {
        return userService.findById(id);
    }


    @PostMapping("/delete")
    public String deleteUser(Long id) {
        userService.deleteById(id);
        return "redirect:admin/";
    }

    @PostMapping("/save")
    public String add(User user, String infoRole) {
        if (infoRole != null) {
            userService.update(user, infoRole);
        } else {
        userService.saveUser(user);
        }
        return "redirect:admin/";
    }

    @GetMapping("/")
    public String userPage(Model model, Principal principal) {
        User mUser = userService.findByUsername(principal.getName());
        String headMsg = mUser.getEmail() + " with roles: " + mUser.getRoles();
        model.addAttribute("mUser", mUser);
        model.addAttribute("headMsg", headMsg);
        return "userpage";
    }

}
