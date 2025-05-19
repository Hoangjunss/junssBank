package com.hoangjunss.junsBank.mapper;

import com.hoangjunss.junsBank.dto.account.AccountCreateDTO;
import com.hoangjunss.junsBank.dto.account.AccountDTO;
import com.hoangjunss.junsBank.entity.user.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public Account toEntity(AccountCreateDTO dto) {
        return Account.builder()
                .userCode(dto.getUserCode())
                .password(dto.getPassword())
                .build();
    }

    public AccountDTO toDTO(Account account) {
        return AccountDTO.builder()
                .id(account.getId())
                .userCode(account.getUserCode())
                .password(account.getPassword())
                // mapper usserDTO
                .build();
    }
}
