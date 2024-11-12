package com.hoangjunss.junsBank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailVerificationEvent {

        private String email;
        private String verificationCode;




}
