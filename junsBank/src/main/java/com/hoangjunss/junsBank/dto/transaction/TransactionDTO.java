package com.hoangjunss.junsBank.dto.transaction;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private Integer id;
    private String transactionId;
    private String fromAccount;
    private String toAccount;
    private double amount;
    private String transactionType;
    private String status;
    private LocalDateTime transactionDate;
}
