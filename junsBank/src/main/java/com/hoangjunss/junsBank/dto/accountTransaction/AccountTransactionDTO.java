package com.hoangjunss.junsBank.dto.accountTransaction;

import com.hoangjunss.junsBank.dto.user.UserDTO;
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
public class AccountTransactionDTO {
    private Integer id;
    private String accountNumber;
    private double balance;
    private boolean status;
    private String accountType;
    private LocalDate createDate;
    private UserDTO user;
}
