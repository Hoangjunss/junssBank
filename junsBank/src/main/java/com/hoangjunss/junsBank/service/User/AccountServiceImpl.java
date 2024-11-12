package com.hoangjunss.junsBank.service.User;

import com.hoangjunss.junsBank.entity.user.Account;
import com.hoangjunss.junsBank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public void createAccount(Account account) {
        account.setId(getGenerationId());
        accountRepository.save(account);
    }
    public Integer getGenerationId() {
        UUID uuid = UUID.randomUUID();
        return (int) (uuid.getMostSignificantBits() & 0xFFFFFFFFL);
    }
}
