package com.mpolec.lab7.controller;

import com.mpolec.lab7.dao.userDao;
import com.mpolec.lab7.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private userDao dao;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model m) {

        m.addAttribute("user", new User());

        return "register";
    }

    @PostMapping("/register")
    public String registerPagePOST(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.save(user);
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profilePage(Model m, Principal principal) {
        m.addAttribute("user", dao.findByLogin(principal.getName()));
        return "profile";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("usersList", dao.findAll());
        return "users";
    }

    @GetMapping("/edit")
    public String editUser(Model model) {

        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("currentUser", dao.findByLogin(login));

        return "edit";
    }

    @PostMapping("/edit")
    public String editUser(@Valid @ModelAttribute("currentUser") User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "edit";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.save(user);

        return "redirect:/profile";
    }

    @GetMapping("/delete")
    public String deleteUser() {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        dao.delete(dao.findByLogin(login));

        return "redirect:/logout";
    }
}
