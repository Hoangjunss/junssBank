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
public class TransactionCreateDTO {
    @NotNull
    private String transactionId;

    private String fromAccount;

    private String toAccount;

    @NotNull
    private Double amount;

    @NotNull
    private String transactionType;

    @NotNull
    private String status;

    @NotNull
    private LocalDateTime transactionDate;
}