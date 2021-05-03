package com.yoonho.bowlog.feed.controller;

import com.yoonho.bowlog.user.domain.Account;
import com.yoonho.bowlog.user.security.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FeedController {
    @GetMapping("/feed")
    public String getFeedPage(@CurrentUser Account account, Model model) {
        if (account != null) {
            model.addAttribute(account);
        }
        return "feed";
    }
}
