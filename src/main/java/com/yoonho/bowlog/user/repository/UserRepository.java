package com.yoonho.bowlog.user.repository;

import com.yoonho.bowlog.user.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<Account, Long> {
    Account findByNickname(String nickname);

    Account findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);
}
