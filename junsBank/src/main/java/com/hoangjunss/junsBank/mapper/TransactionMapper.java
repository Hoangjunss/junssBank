package com.hoangjunss.junsBank.mapper;


import com.hoangjunss.junsBank.dto.transaction.TransactionCreateDTO;
import com.hoangjunss.junsBank.dto.transaction.TransactionDTO;
import com.hoangjunss.junsBank.entity.user.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    public Transaction toEntity(TransactionCreateDTO dto) {
        return Transaction.builder()
                .transactionId(dto.getTransactionId())
                .fromAccount(dto.getFromAccount())
                .toAccount(dto.getToAccount())
                .amount(dto.getAmount())
                .transactionType(dto.getTransactionType())
                .status(dto.getStatus())
                .transactionDate(dto.getTransactionDate())
                .build();
    }

    public TransactionDTO toDTO(Transaction entity) {
        return TransactionDTO.builder()
                .id(entity.getId())
                .transactionId(entity.getTransactionId())
                .fromAccount(entity.getFromAccount())
                .toAccount(entity.getToAccount())
                .amount(entity.getAmount())
                .transactionType(entity.getTransactionType())
                .status(entity.getStatus())
                .transactionDate(entity.getTransactionDate())
                .build();
    }
}