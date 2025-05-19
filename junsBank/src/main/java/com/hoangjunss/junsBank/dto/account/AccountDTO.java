package com.hoangjunss.junsBank.dto.account;
import com.hoangjunss.junsBank.dto.user.UserDTO;
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
public class AccountDTO {
    private Integer id;
    private String userCode;
    private String password;
    private UserDTO user;
}
