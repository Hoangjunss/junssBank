package com.hoangjunss.junsBank.listener;

import com.hoangjunss.junsBank.dto.user.UserCreateDTO;
import com.hoangjunss.junsBank.entity.user.Account;
import com.hoangjunss.junsBank.entity.user.User;
import com.hoangjunss.junsBank.mapper.AccountMapper;
import com.hoangjunss.junsBank.service.User.AccountService;
import com.hoangjunss.junsBank.service.User.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
@Slf4j
public class AccountEventListener {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private UserService userService;
    @KafkaListener(topics = "create-user", groupId = "my-group")

    public void consumeCreateUserEvent(String identificationNumber) {
        log.info("Create account: " + identificationNumber);

        accountService.createAccount(identificationNumber);

    }
}
