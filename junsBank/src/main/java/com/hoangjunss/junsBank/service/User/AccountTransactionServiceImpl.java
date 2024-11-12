package com.hoangjunss.junsBank.service.User;

import com.hoangjunss.junsBank.entity.user.AccountTransaction;
import com.hoangjunss.junsBank.repository.AccountTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class AccountTransactionServiceImpl implements AccountTransactionService{
    @Autowired
    private AccountTransactionRepository accountTransactionRepository;
    @Override
    public void createAccountTransaction(AccountTransaction accountTransaction) {
        accountTransaction.setId(getGenerationId());
        accountTransactionRepository.save(accountTransaction);
    }
    public Integer getGenerationId() {
        UUID uuid = UUID.randomUUID();
        return (int) (uuid.getMostSignificantBits() & 0xFFFFFFFFL);
    }
}
