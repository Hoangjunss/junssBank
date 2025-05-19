package com.hoangjunss.junsBank.dto.historyChangeStatusTransaction;

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
public class HistoryChangeStatusTransactionCreateDTO {
    @NotNull
    private Integer transactionId;

    private Boolean oldStatus;

    @NotNull
    private Boolean newStatus;

    @NotNull
    private LocalDateTime updateDate;
}
