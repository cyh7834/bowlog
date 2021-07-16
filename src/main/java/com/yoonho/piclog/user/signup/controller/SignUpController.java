package com.yoonho.piclog.user.signup.controller;

import com.yoonho.piclog.user.domain.Account;
import com.yoonho.piclog.user.security.CurrentUser;
import com.yoonho.piclog.user.signup.dto.SignUpForm;
import com.yoonho.piclog.user.signup.service.SignUpService;
import com.yoonho.piclog.user.signup.validator.SignUpFormValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class SignUpController {
    private final SignUpFormValidator signUpFormValidator;
    private final SignUpService signUpService;

    @InitBinder("signUpForm")
    public void signUpFormInitBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(signUpFormValidator);
    }

    @GetMapping("/sign-up")
    public String signUpPage(@CurrentUser Account account, Model model) {
        if (account != null)
            return "redirect:/";

        model.addAttribute(new SignUpForm());

        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String signUpSubmit(@Valid SignUpForm signUpForm, Errors errors, RedirectAttributes attributes) {
        if (errors.hasErrors()) {
            return "sign-up";
        }

        signUpService.signUpNewUser(signUpForm);
        attributes.addFlashAttribute("message", "회원 가입을 완료했습니다.");
        return "redirect:/";
    }
}
