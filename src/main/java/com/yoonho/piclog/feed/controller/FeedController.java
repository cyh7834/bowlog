package com.yoonho.piclog.feed.controller;

import com.yoonho.piclog.user.domain.Account;
import com.yoonho.piclog.user.security.CurrentUser;
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
