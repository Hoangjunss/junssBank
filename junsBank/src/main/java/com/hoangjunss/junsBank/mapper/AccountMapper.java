package com.hoangjunss.junsBank.mapper;

import com.hoangjunss.junsBank.dto.user.AccountDTO;
import com.hoangjunss.junsBank.dto.user.UserCreateDTO;
import com.hoangjunss.junsBank.entity.user.Account;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountMapper {
    @Autowired
    private ModelMapper modelMapper;
    public Account createToEntity(AccountDTO accountDTO){
        return modelMapper.map(accountDTO,Account.class);
    }
}
