package com.hoangjunss.junsBank.dto.historyChangeStatusTransaction;

import com.hoangjunss.junsBank.dto.transaction.TransactionDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoryChangeStatusTransactionDTO {
    private Integer id;
    private TransactionDTO transaction;
    private boolean oldStatus;
    private boolean newStatus;
    private LocalDate updateDate;
}
