package com.yoonho.bowlog.user.service;

import com.yoonho.bowlog.user.domain.Account;
import com.yoonho.bowlog.user.domain.UserAccount;
import com.yoonho.bowlog.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account account = userRepository.findByEmail(s);

        if (account == null)
            throw new UsernameNotFoundException(s);

        return new UserAccount(account);
    }
}
