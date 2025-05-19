package com.hoangjunss.junsBank.service.impl;


import com.hoangjunss.junsBank.dto.account.AccountCreateDTO;
import com.hoangjunss.junsBank.dto.account.AccountDTO;
import com.hoangjunss.junsBank.dto.account.AccountUpdateDTO;
import com.hoangjunss.junsBank.entity.user.Account;
import com.hoangjunss.junsBank.mapper.AccountMapper;
import com.hoangjunss.junsBank.repository.AccountRepository;
import com.hoangjunss.junsBank.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;



    @Override
    public AccountDTO createAccount(AccountCreateDTO dto) {
        Account entity = accountMapper.toEntity(dto);
        Account saved = accountRepository.save(entity);
        return accountMapper.toDTO(saved);
    }

    @Override
    public AccountDTO updateAccount(AccountUpdateDTO dto) {
       // Account entity = accountMapper.convertUpdateToEntity(dto);
       // Account updated = accountRepository.save(entity);
        //return accountMapper.toDTO(updated);
        return null;
    }

    @Override
    public AccountDTO getAccountById(Integer id) {
        Account entity = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found: " + id));
        return accountMapper.toDTO(entity);
    }

    @Override
    public List<AccountDTO> getAllAccounts() {
        List<Account> entities = accountRepository.findAll();
        return entities.stream()
                .map(accountMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Integer id) {
        accountRepository.deleteById(id);
    }
}