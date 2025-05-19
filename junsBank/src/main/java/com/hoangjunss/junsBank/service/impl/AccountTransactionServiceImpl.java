package com.hoangjunss.junsBank.service.impl;

import com.hoangjunss.junsBank.dto.accountTransaction.AccountTransactionCreateDTO;
import com.hoangjunss.junsBank.dto.accountTransaction.AccountTransactionDTO;
import com.hoangjunss.junsBank.dto.accountTransaction.AccountTransactionUpdateDTO;
import com.hoangjunss.junsBank.entity.user.AccountTransaction;
import com.hoangjunss.junsBank.mapper.AccountTransactionMapper;
import com.hoangjunss.junsBank.repository.AccountTransactionRepository;
import com.hoangjunss.junsBank.service.AccountTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountTransactionServiceImpl implements AccountTransactionService {

    private final AccountTransactionRepository repository;
    private final AccountTransactionMapper mapper;



    @Override
    public AccountTransactionDTO createAccountTransaction(AccountTransactionCreateDTO dto) {
        AccountTransaction entity = mapper.toEntity(dto);
        AccountTransaction saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    @Override
    public AccountTransactionDTO updateAccountTransaction(AccountTransactionUpdateDTO dto) {
       /* AccountTransaction entity = mapper.convertUpdateToEntity(dto);
        AccountTransaction updated = repository.save(entity);
        return mapper.toAccountTransactionDTO(updated);*/
        return  null;
    }

    @Override
    public AccountTransactionDTO getAccountTransactionById(Integer id) {
        AccountTransaction entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("AccountTransaction not found: " + id));
        return mapper.toDTO(entity);
    }

    @Override
    public List<AccountTransactionDTO> getAllAccountTransactions() {
        List<AccountTransaction> list = repository.findAll();
        return list.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccountTransaction(Integer id) {
        repository.deleteById(id);
    }
}