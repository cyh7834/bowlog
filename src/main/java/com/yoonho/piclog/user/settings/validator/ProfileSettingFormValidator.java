package com.yoonho.piclog.user.settings.validator;

import com.yoonho.piclog.user.repository.UserRepository;
import com.yoonho.piclog.user.settings.dto.ProfileSettingForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class ProfileSettingFormValidator implements Validator {
    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(ProfileSettingForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ProfileSettingForm profileSettingForm = (ProfileSettingForm) o;

        if (userRepository.existsByNickname(profileSettingForm.getNickname())) {
            errors.rejectValue("nickname", "invalid.nickname", new Object[]{profileSettingForm.getNickname()}, "이미 사용중인 닉네임입니다.");
        }
    }
}
