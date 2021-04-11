package com.yoonho.bowlog.user.login.controller;

import com.yoonho.bowlog.user.domain.Account;
import com.yoonho.bowlog.user.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {
    @GetMapping("/login")
    public String loginPage(@CurrentUser Account account, Model model) {
        if (account != null) {
            return "redirect:/";
        }

        return "login";
    }
}
