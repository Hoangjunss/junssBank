package com.hoangjunss.junsBank.service.User;

import com.hoangjunss.junsBank.entity.user.Account;
import com.hoangjunss.junsBank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void createAccount(Account account) {
        account.setId(getGenerationId());
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
    }
    public Integer getGenerationId() {
        UUID uuid = UUID.randomUUID();
        return (int) (uuid.getMostSignificantBits() & 0xFFFFFFFFL);
    }
}
