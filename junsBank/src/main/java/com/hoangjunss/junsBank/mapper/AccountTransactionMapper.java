package com.hoangjunss.junsBank.mapper;



import com.hoangjunss.junsBank.dto.accountTransaction.AccountTransactionCreateDTO;
import com.hoangjunss.junsBank.dto.accountTransaction.AccountTransactionDTO;
import com.hoangjunss.junsBank.entity.user.AccountTransaction;
import org.springframework.stereotype.Component;

@Component
public class AccountTransactionMapper {
    public AccountTransaction toEntity(AccountTransactionCreateDTO dto) {
        return AccountTransaction.builder()
                .accountNumber(dto.getAccountNumber())
                .balance(dto.getBalance())
                //.status(dto.isStatus())
                .accountType(dto.getAccountType())
                //.createDate(dto.getCreateDate())
                .build();
    }

    public AccountTransactionDTO toDTO(AccountTransaction entity) {
        return AccountTransactionDTO.builder()
                .id(entity.getId())
                .accountNumber(entity.getAccountNumber())
                .balance(entity.getBalance())
                .status(entity.isStatus())
                .accountType(entity.getAccountType())
                .createDate(entity.getCreateDate())
               // .userId(entity.getUser().getId())
                .build();
    }
}