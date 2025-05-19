package com.hoangjunss.junsBank.service.impl;


import com.hoangjunss.junsBank.dto.transaction.TransactionCreateDTO;
import com.hoangjunss.junsBank.dto.transaction.TransactionDTO;
import com.hoangjunss.junsBank.dto.transaction.TransactionUpdateDTO;
import com.hoangjunss.junsBank.entity.user.Transaction;
import com.hoangjunss.junsBank.mapper.TransactionMapper;
import com.hoangjunss.junsBank.repository.TransactionRepository;
import com.hoangjunss.junsBank.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;


    @Override
    public TransactionDTO createTransaction(TransactionCreateDTO transactionCreateDTO) {
        Transaction transaction = transactionMapper.toEntity(transactionCreateDTO);
        Transaction saved = transactionRepository.save(transaction);
        return transactionMapper.toDTO(saved);
    }

    @Override
    public TransactionDTO updateTransaction(TransactionUpdateDTO transactionUpdateDTO) {
       /* Transaction transaction = transactionMapper.toEntity(transactionUpdateDTO);
        Transaction updated = transactionRepository.save(transaction);
        return transactionMapper.toDTO(updated);*/
        return null;
    }

    @Override
    public TransactionDTO getTransactionById(Integer id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id = " + id));
        return transactionMapper.toDTO(transaction);
    }

    @Override
    public List<TransactionDTO> getAllTransactions() {
        return transactionRepository.findAll()
                .stream()
                .map(transactionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTransaction(Integer id) {
        transactionRepository.deleteById(id);
    }
}