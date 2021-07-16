package com.yoonho.piclog.user.signup.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class SignUpForm {
    @NotBlank
    @Length(min = 2, max = 8, message = "닉네임은 두 글자 이상 여덟 글자 이하여야 합니다.")
    @Pattern(regexp ="^[가-힣ㄱ-ㅎa-zA-Z0-9._-]{2,8}$", message = "닉네임 형식이 올바르지 않습니다.")
    private String nickname;

    @Email
    @NotBlank
    @Pattern(regexp = "[a-z0-9]+@[a-z0-9]+\\.[a-z]{2,}$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank
    @Length(min = 8, max = 50)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$", message = "비밀번호는 최소 8자 이상, 하나 이상의 문자, 숫자, 특수 문자를 포함해야 합니다.")
    private String password;

    @NotBlank
    @Length(min = 8, max = 50)
    private String passwordConfirm;

    @NotEmpty
    private String gender;
}
