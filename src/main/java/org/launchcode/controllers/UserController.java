package org.launchcode.controllers;

import jakarta.validation.Valid;
import org.launchcode.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
public class UserController {

    @GetMapping("/add")
    public String displayAddUserForm(Model model) {
        model.addAttribute(new User());
        return "user/add";
    }


    @PostMapping
    public String processAddUserForm(Model model, @ModelAttribute @Valid User user, Errors erros) {
        model.addAttribute("user", user);
        if (erros.hasErrors() && !user.getPassword().equals(user.getVerifyPassword())) {
            model.addAttribute("error", user.getVerifyPassword());
            return "user/add";
        } else {
            return "user/index";
        }

    }
}