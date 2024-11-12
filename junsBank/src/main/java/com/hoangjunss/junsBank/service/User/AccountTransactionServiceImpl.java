package com.hoangjunss.junsBank.service.User;

import com.hoangjunss.junsBank.entity.user.AccountTransaction;
import com.hoangjunss.junsBank.entity.user.User;
import com.hoangjunss.junsBank.repository.AccountTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.UUID;

public class AccountTransactionServiceImpl implements AccountTransactionService{
    @Autowired
    private AccountTransactionRepository accountTransactionRepository;
    @Autowired
    private UserService userService;
    @Override
    public void createAccountTransaction(String identificationNumber) {
        User user=userService.findByIdentificationNumber(identificationNumber);
        AccountTransaction accountTransaction=AccountTransaction.builder()
                .id(getGenerationId())
                .accountNumber(user.getIdentificationNumber())
                .status(true).createDate(LocalDate.now())
                .user(user)
                .accountType("NEW")
                .build();
        accountTransactionRepository.save(accountTransaction);
    }
    public Integer getGenerationId() {
        UUID uuid = UUID.randomUUID();
        return (int) (uuid.getMostSignificantBits() & 0xFFFFFFFFL);
    }
}
