package com.vjava.transfer1.controller;


import com.vjava.transfer1.model.Role;
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
import java.util.Set;

@Controller
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showAll(Model model, Principal principal) {
        List<User> users = userService.findAll();
        User mUser = userService.findByUsername(principal.getName());
        model.addAttribute("people", users);
        model.addAttribute("mail", mUser.getEmail());
        return "index";
    }

    @GetMapping("/{id}/edit")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("person", userService.findById(id));
        return "edit";
    }

   /* @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") User user, @PathVariable("id") Long id) {
        userService.update(user, id);
        return "redirect:/";
    }*/

    @GetMapping("/findOne")
    @ResponseBody
    public User findOne(Long id) {
        return userService.findById(id);
    }

   /* @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/";
    }*/
    @GetMapping("/delete")
    public String deleteUser(Long id) {
        userService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/new")
    public String addForm(Model model, Principal principal) {
        User mUser = userService.findByUsername(principal.getName());
        model.addAttribute("mail", mUser.getEmail());
        model.addAttribute("person", new User());
        return "new";
    }

    @PostMapping("/save")
    public String add(User user, String infoRole) {
        if (infoRole != null) {
            userService.update(user, infoRole);
        } else {
        userService.saveUser(user);
        }
        return "redirect:/";
    }

    @GetMapping("page")
    public String userPage(Model model, Principal principal) {
        User mUser = userService.findByUsername(principal.getName());
        String headMsg = mUser.getEmail() + " with roles: " + mUser.getRoles();
        model.addAttribute("mUser", mUser);
        model.addAttribute("headMsg", headMsg);
        return "page";
    }



}
