package com.hoangjunss.junsBank.listener;

import com.hoangjunss.junsBank.dto.user.UserCreateDTO;
import com.hoangjunss.junsBank.entity.user.Account;
import com.hoangjunss.junsBank.entity.user.User;
import com.hoangjunss.junsBank.mapper.AccountMapper;
import com.hoangjunss.junsBank.service.User.AccountService;
import com.hoangjunss.junsBank.service.User.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class AccountEventListener {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private UserService userService;
    @KafkaListener(topics = "create-user", groupId = "my-group")
    @Transactional
    public void consumeCreateUserEvent(UserCreateDTO userCreateDTO) {
        System.out.println("Create account: " + userCreateDTO.toString());

        Account account=accountMapper.createToEntity(userCreateDTO);

        User user=userService.findByIdentificationNumber(userCreateDTO.getIdentificationNumber());
        account.setUser(user);

        accountService.createAccount(account);
        // Thực hiện các xử lý cần thiết với message
    }
}
