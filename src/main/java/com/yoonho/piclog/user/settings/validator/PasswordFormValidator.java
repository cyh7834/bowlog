package com.yoonho.piclog.user.settings.validator;

import com.yoonho.piclog.user.settings.dto.PasswordForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class PasswordFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(PasswordForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PasswordForm passwordForm = (PasswordForm) o;
        String newPassword = passwordForm.getNewPassword();
        String newPasswordConfirm = passwordForm.getNewPasswordConfirm();

        if (!newPassword.equals(newPasswordConfirm)) {
            errors.rejectValue("newPassword", "wrong.value","재입력된 새 비밀번호가 다릅니다.");
        }

    }
}
