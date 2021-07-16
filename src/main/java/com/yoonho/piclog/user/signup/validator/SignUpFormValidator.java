package com.yoonho.piclog.user.signup.validator;

import com.yoonho.piclog.user.repository.UserRepository;
import com.yoonho.piclog.user.signup.dto.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class SignUpFormValidator implements Validator {
    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(SignUpForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SignUpForm signUpForm = (SignUpForm) o;
        String password = signUpForm.getPassword();
        String passwordConfirm = signUpForm.getPasswordConfirm();

        if (userRepository.existsByEmail(signUpForm.getEmail())) {
            errors.rejectValue("email", "invalid.email", new Object[]{signUpForm.getEmail()}, "이미 사용중인 이메일입니다.");
        }
        if (userRepository.existsByNickname(signUpForm.getNickname())) {
            errors.rejectValue("nickname", "invalid.nickname", new Object[]{signUpForm.getNickname()}, "이미 사용중인 닉네임입니다.");
        }
        if (!password.equals(passwordConfirm)) {
            errors.rejectValue("passwordConfirm", "invalid.passwordConfirm", new Object[]{signUpForm.getPasswordConfirm()}, "재입력된 비밀번호가 다릅니다.");
        }
    }
}
