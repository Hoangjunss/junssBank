package com.hoangjunss.junsBank.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer id;
    private String fullName;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String identificationNumber;
    private boolean status;
    private String userCode;


}

