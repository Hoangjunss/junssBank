package com.hoangjunss.junsBank.dto.historyChangeAccountPassword;

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
public class HistoryChangePasswordAccountUpdateDTO {
    private String newPassword;
    private LocalDateTime changeDate;
}