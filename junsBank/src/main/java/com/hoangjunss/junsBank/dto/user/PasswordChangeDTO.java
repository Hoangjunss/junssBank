package com.hoangjunss.junsBank.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PasswordChangeDTO {
    private String identificationNumber;
    private String oldPassword;
    private String newPassword;
}