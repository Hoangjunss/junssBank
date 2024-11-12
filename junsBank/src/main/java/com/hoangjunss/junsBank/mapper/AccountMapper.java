package com.hoangjunss.junsBank.mapper;

import com.hoangjunss.junsBank.dto.user.UserCreateDTO;
import com.hoangjunss.junsBank.entity.user.Account;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountMapper {
    @Autowired
    private ModelMapper modelMapper;
    public Account createToEntity(UserCreateDTO userCreateDTO){
        return Account.builder()
                .password(userCreateDTO.getPassword())
                .userCode(userCreateDTO.getPhoneNumber())
                .build();
    }
}
