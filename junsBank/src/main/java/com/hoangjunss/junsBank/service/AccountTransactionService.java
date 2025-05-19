package com.hoangjunss.junsBank.service;


import com.hoangjunss.junsBank.dto.accountTransaction.AccountTransactionCreateDTO;
import com.hoangjunss.junsBank.dto.accountTransaction.AccountTransactionDTO;
import com.hoangjunss.junsBank.dto.accountTransaction.AccountTransactionUpdateDTO;

import java.util.List;

public interface AccountTransactionService {
    AccountTransactionDTO createAccountTransaction(AccountTransactionCreateDTO dto);
    AccountTransactionDTO updateAccountTransaction(AccountTransactionUpdateDTO dto);
    AccountTransactionDTO getAccountTransactionById(Integer id);
    List<AccountTransactionDTO> getAllAccountTransactions();
    void deleteAccountTransaction(Integer id);
}