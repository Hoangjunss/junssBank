package com.hoangjunss.junsBank.dto.historyChangeAccountPassword;

import com.hoangjunss.junsBank.dto.user.UserDTO;
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
public class HistoryChangePasswordAccountDTO {
    private Integer id;
    private String oldPassword;
    private String newPassword;
    private LocalDateTime changeDate;
    private UserDTO user;
}
