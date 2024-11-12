package com.hoangjunss.junsBank.listener;

import com.hoangjunss.junsBank.dto.user.UserCreateDTO;
import com.hoangjunss.junsBank.entity.user.AccountTransaction;
import com.hoangjunss.junsBank.entity.user.User;
import com.hoangjunss.junsBank.mapper.AccountTransactionMapper;
import com.hoangjunss.junsBank.service.User.AccountTransactionService;
import com.hoangjunss.junsBank.service.User.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class AccountTransactionEventListener {
    @Autowired
    private AccountTransactionService accountTransactionService;
    @Autowired
    private AccountTransactionMapper accountTransactionMapper;
    @Autowired
    private UserService userService;
    @KafkaListener(topics = "create-user", groupId = "my-group")
    @Transactional
    public void consumeCreateUserEvent(UserCreateDTO userCreateDTO) {
        System.out.println("Create accountTRansaction: " + userCreateDTO.toString());

        AccountTransaction accountTransaction=accountTransactionMapper.createToEntity(userCreateDTO);

        User user=userService.findByIdentificationNumber(userCreateDTO.getIdentificationNumber());
        accountTransaction.setUser(user);

       accountTransactionService.createAccountTransaction(accountTransaction);
    }
}
