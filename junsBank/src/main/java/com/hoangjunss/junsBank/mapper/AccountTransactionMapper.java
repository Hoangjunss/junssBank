package com.hoangjunss.junsBank.mapper;

import com.hoangjunss.junsBank.dto.user.UserCreateDTO;
import com.hoangjunss.junsBank.entity.user.AccountTransaction;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class AccountTransactionMapper {
    @Autowired
    private ModelMapper modelMapper;
    public AccountTransaction createToEntity(UserCreateDTO userCreateDTO){
        return AccountTransaction.builder()
                .accountType("")
                .createDate(LocalDate.now())
                .balance(0).status(true)
                .accountNumber(userCreateDTO.getPhoneNumber())
                .build();
    }
}
