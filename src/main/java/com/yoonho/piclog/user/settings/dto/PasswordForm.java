package com.yoonho.piclog.user.settings.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class PasswordForm {
    @NotBlank
    @Length(min = 8, max = 50)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$", message = "비밀번호는 최소 8자 이상, 하나의 문자, 숫자, 특수 문자를 포함해야 합니다.")
    private String currentPassword;

    @NotBlank
    @Length(min = 8, max = 50)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$", message = "비밀번호는 최소 8자 이상, 하나의 문자, 숫자, 특수 문자를 포함해야 합니다.")
    private String newPassword;

    @NotBlank
    @Length(min = 8, max = 50)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$", message = "비밀번호는 최소 8자 이상, 하나의 문자, 숫자, 특수 문자를 포함해야 합니다.")
    private String newPasswordConfirm;
}
