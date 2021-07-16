package com.yoonho.piclog.timeline.controller;

import com.yoonho.piclog.user.domain.Account;
import com.yoonho.piclog.user.security.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TimelineController {
    @GetMapping("/timeline")
    public String getTimelinePage(@CurrentUser Account account, Model model) {
        if (account != null) {
            model.addAttribute(account);
        }
        return "timeline";
    }
}
