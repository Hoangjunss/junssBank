package com.hoangjunss.junsBank.service;


import com.hoangjunss.junsBank.dto.account.AccountCreateDTO;
import com.hoangjunss.junsBank.dto.account.AccountDTO;
import com.hoangjunss.junsBank.dto.account.AccountUpdateDTO;

import java.util.List;

public interface AccountService {
    AccountDTO createAccount(AccountCreateDTO accountCreateDTO);
    AccountDTO updateAccount(AccountUpdateDTO accountUpdateDTO);
    AccountDTO getAccountById(Integer id);
    List<AccountDTO> getAllAccounts();
    void deleteAccount(Integer id);
}