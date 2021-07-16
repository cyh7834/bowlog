package com.yoonho.piclog.user.settings.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class ProfileSettingForm {
    @NotBlank
    @Length(min = 2, max = 8, message = "닉네임은 두 글자 이상 여덟 글자 이하여야 합니다.")
    @Pattern(regexp ="^[가-힣ㄱ-ㅎa-zA-Z0-9._-]{2,8}$", message = "닉네임 형식이 올바르지 않습니다.")
    private String nickname;

    @Length(max = 30, message = "자기소개는 30자 이하여야 합니다.")
    private String bio;

    @NotEmpty
    private String gender;

    private String ProfileImage;
}
