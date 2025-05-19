package com.hoangjunss.junsBank.mapper;

import com.hoangjunss.junsBank.dto.user.UserCreateDTO;
import com.hoangjunss.junsBank.dto.user.UserDTO;
import com.hoangjunss.junsBank.entity.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserCreateDTO dto) {
        return User.builder()
                .fullName(dto.getFullName())
                .phoneNumber(dto.getPhoneNumber())
                .dateOfBirth(dto.getDateOfBirth())
                .identificationNumber(dto.getIdentificationNumber())
                .status(dto.isStatus())
                .build();
    }

    public UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .phoneNumber(user.getPhoneNumber())
                .dateOfBirth(user.getDateOfBirth())
                .identificationNumber(user.getIdentificationNumber())
                .status(user.isStatus())
                .build();
    }
}
