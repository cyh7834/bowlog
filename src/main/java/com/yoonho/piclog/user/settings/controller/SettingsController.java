package com.yoonho.piclog.user.settings.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoonho.piclog.user.domain.Account;
import com.yoonho.piclog.user.repository.UserRepository;
import com.yoonho.piclog.user.security.CurrentUser;
import com.yoonho.piclog.user.settings.dto.PasswordForm;
import com.yoonho.piclog.user.settings.dto.ProfileSettingForm;
import com.yoonho.piclog.user.settings.service.SettingsService;
import com.yoonho.piclog.user.settings.validator.PasswordFormValidator;
import com.yoonho.piclog.zone.domain.Zone;
import com.yoonho.piclog.zone.dto.ZoneForm;
import com.yoonho.piclog.zone.repository.ZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class SettingsController {
    private final SettingsService settingsService;
    private final UserRepository userRepository;
    private final ZoneRepository zoneRepository;
    private final ObjectMapper objectMapper;
    private final PasswordFormValidator passwordFormValidator;

    @InitBinder("passwordForm")
    public void passwordFormInitBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(passwordFormValidator);
    }

    @GetMapping("/settings/profile")
    public String getSettingPage(@CurrentUser Account account, Model model) {
        model.addAttribute(account);
        model.addAttribute(new ProfileSettingForm());

        return "settings/profile";
    }

    @PostMapping("/settings/profile")
    public String setProfile(@CurrentUser Account account, @Valid ProfileSettingForm profileSettingForm, Errors errors,
                             Model model, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            model.addAttribute(account);
            return "settings/profile";
        }
        String currentNickname = account.getNickname();
        String newNickname = profileSettingForm.getNickname();
        if (!currentNickname.equals(newNickname)) {
            Account user = userRepository.findByNickname(newNickname);

            if (user != null) {
                model.addAttribute(account);
                model.addAttribute("nicknameError", "이미 존재하는 닉네임입니다.");
                return "settings/profile";
            }
        }
        settingsService.updateProfile(account, profileSettingForm);
        redirectAttributes.addFlashAttribute("message", "프로필을 변경하였습니다.");
        return "redirect:/";
    }

    @GetMapping("/settings/zone")
    public String getZonePage(@CurrentUser Account account, Model model) throws JsonProcessingException {
        model.addAttribute(account);

        Set<Zone> userZones = settingsService.getZones(account);
        model.addAttribute("userZones", userZones.stream().map(Zone::toString).collect(Collectors.toList()));

        List<String> allZones = zoneRepository.findAll().stream().map(Zone::toString).collect(Collectors.toList());
        model.addAttribute("allZoneList", objectMapper.writeValueAsString(allZones));

        return "settings/zone";
    }

    @PostMapping("/settings/zone/add")
    @ResponseBody
    public ResponseEntity addZone(@CurrentUser Account account, @RequestBody ZoneForm zoneForm) {
        Zone zone = zoneRepository.findByCityAndProvince(zoneForm.getCityName(), zoneForm.getProvinceName());
        if (zone == null) {
            return ResponseEntity.badRequest().build();
        }

        settingsService.addZone(account, zone);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/settings/zone/remove")
    @ResponseBody
    public ResponseEntity removeZone(@CurrentUser Account account, @RequestBody ZoneForm zoneForm) {
        Zone zone = zoneRepository.findByCityAndProvince(zoneForm.getCityName(), zoneForm.getProvinceName());
        if (zone == null) {
            return ResponseEntity.badRequest().build();
        }

        settingsService.removeZone(account, zone);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/settings/password")
    public String getPasswordPage(@CurrentUser Account account, Model model) {
        model.addAttribute(account);
        model.addAttribute(new PasswordForm());

        return "settings/password";
    }

    @PostMapping("/settings/password")
    public String setNewPassword(@CurrentUser Account account, @Valid PasswordForm passwordForm, Errors errors
            , Model model, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            model.addAttribute(account);

            return "settings/password";
        }
        String currentPassword = passwordForm.getCurrentPassword();
        String newPassword = passwordForm.getNewPassword();

        boolean isPasswordCorrect = settingsService.checkCurrentPassword(account, currentPassword);
        if (!isPasswordCorrect) {
            model.addAttribute("currentPasswordError", "비밀번호가 다릅니다.");
            return "settings/password";
        }

        settingsService.setNewPassword(account, newPassword);
        redirectAttributes.addFlashAttribute("message", "비밀번호를 변경했습니다.");
        return "redirect:/";
    }
}
