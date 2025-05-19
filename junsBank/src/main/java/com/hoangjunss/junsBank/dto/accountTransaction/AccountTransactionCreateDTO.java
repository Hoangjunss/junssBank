package com.hoangjunss.junsBank.dto.accountTransaction;

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
public class AccountTransactionCreateDTO {
    @NotNull
    private String accountNumber;

    @NotNull
    private Double balance;

    @NotNull
    private Boolean status;

    @NotNull
    private String accountType;

    @NotNull
    private LocalDateTime createDate;

    @NotNull
    private Integer userId;
}