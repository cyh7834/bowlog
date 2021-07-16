package com.yoonho.piclog.user.settings.service;

import com.yoonho.piclog.user.domain.Account;
import com.yoonho.piclog.user.domain.UserAccount;
import com.yoonho.piclog.user.repository.UserRepository;
import com.yoonho.piclog.user.settings.dto.ProfileSettingForm;
import com.yoonho.piclog.zone.domain.Zone;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class SettingsService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    public Set<Zone> getZones(Account account) {
        Optional<Account> byNickname = userRepository.findById(account.getId());
        return byNickname.orElseThrow().getZone();
    }

    public void addZone(Account account, Zone zone) {
        Optional<Account> byId = userRepository.findById(account.getId());
        byId.ifPresent(presentAccount -> presentAccount.getZone().add(zone));
    }

    public void removeZone(Account account, Zone zone) {
        Optional<Account> byId = userRepository.findById(account.getId());
        byId.ifPresent(presentAccount -> presentAccount.getZone().remove(zone));
    }

    public boolean checkCurrentPassword(Account account, String currentPassword) {
        String password = account.getPassword();

        if (passwordEncoder.matches(currentPassword, password))
            return true;

        return false;
    }

    public void setNewPassword(Account account, String currentPassword) {
        Account user = userRepository.findByEmail(account.getEmail());
        user.setPassword(passwordEncoder.encode(currentPassword));
    }

    public void updateProfile(Account account, ProfileSettingForm profileSettingForm) {
        account.setNickname(profileSettingForm.getNickname());
        account.setProfileImage(profileSettingForm.getProfileImage());
        account.setBio(profileSettingForm.getBio());
        account.setGender(profileSettingForm.getGender());
        userRepository.save(account);

        login(account);
    }

    private void login(Account account) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                new UserAccount(account),
                account.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")));
        SecurityContextHolder.getContext().setAuthentication(token);
    }
}
