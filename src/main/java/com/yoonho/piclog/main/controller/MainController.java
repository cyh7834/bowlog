package com.yoonho.piclog.main.controller;

import com.yoonho.piclog.user.domain.Account;
import com.yoonho.piclog.user.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/")
    public String mainPage(@CurrentUser Account account, Model model) {
        if (account != null) {
            model.addAttribute(account);
        }
        return "main";
    }
}
