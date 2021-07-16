package com.yoonho.piclog.user.login.controller;

import com.yoonho.piclog.user.domain.Account;
import com.yoonho.piclog.user.security.CurrentUser;
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
