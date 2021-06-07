package com.yoonho.bowlog.user.signup.service;

import com.yoonho.bowlog.user.domain.Account;
import com.yoonho.bowlog.user.repository.UserRepository;
import com.yoonho.bowlog.user.signup.dto.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class SignUpService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @PostConstruct
    public void initTestUser() {
        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setEmail("test@naver.com");
        signUpForm.setNickname("TestNick");
        signUpForm.setPassword("testtest1@");
        signUpForm.setPasswordConfirm("testtest1@");
        signUpForm.setGender("male");

        signUpNewUser(signUpForm);
    }

    public Account signUpNewUser(SignUpForm signUpForm) {
        return saveNewUser(signUpForm);
    }

    private Account saveNewUser(SignUpForm signUpForm) {
        Account account = Account.builder()
                .nickname(signUpForm.getNickname())
                .email(signUpForm.getEmail())
                .password(passwordEncoder.encode(signUpForm.getPassword()))
                .gender(signUpForm.getGender())
                .build();

        return userRepository.save(account);
    }
}
