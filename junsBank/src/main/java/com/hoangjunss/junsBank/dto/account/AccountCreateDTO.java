package com.hoangjunss.junsBank.dto.account;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreateDTO {
    @NotNull(message = "User code cannot be null")
    private String userCode;

    @NotNull(message = "Password cannot be null")
    private String password;

    @NotNull(message = "User cannot be null")
    private Integer userId;
}