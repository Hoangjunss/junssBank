package com.hoangjunss.junsBank.service;


import com.hoangjunss.junsBank.dto.transaction.TransactionCreateDTO;
import com.hoangjunss.junsBank.dto.transaction.TransactionDTO;
import com.hoangjunss.junsBank.dto.transaction.TransactionUpdateDTO;

import java.util.List;

public interface TransactionService {
    TransactionDTO createTransaction(TransactionCreateDTO dto);
    TransactionDTO updateTransaction(TransactionUpdateDTO dto);
    TransactionDTO getTransactionById(Integer id);
    List<TransactionDTO> getAllTransactions();
    void deleteTransaction(Integer id);
}