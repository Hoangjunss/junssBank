package com.hoangjunss.junsBank.mapper;


import com.hoangjunss.junsBank.dto.historyChangeAccountPassword.HistoryChangePasswordAccountCreateDTO;
import com.hoangjunss.junsBank.dto.historyChangeAccountPassword.HistoryChangePasswordAccountDTO;
import com.hoangjunss.junsBank.entity.user.HistoryChangePasswordAccount;
import org.springframework.stereotype.Component;

@Component
public class HistoryChangePasswordAccountMapper {
    public HistoryChangePasswordAccount toEntity(HistoryChangePasswordAccountCreateDTO dto) {
        return HistoryChangePasswordAccount.builder()
                .oldPassword(dto.getOldPassword())
                .newPassword(dto.getNewPassword())
                //.changeDate(dto.getChangeDate())
                .build();
    }

    public HistoryChangePasswordAccountDTO toDTO(HistoryChangePasswordAccount entity) {
        return HistoryChangePasswordAccountDTO.builder()
                .id(entity.getId())
                .oldPassword(entity.getOldPassword())
                .newPassword(entity.getNewPassword())
              //  .changeDate(entity.getChangeDate())
                //.userId(entity.getUser().getId())
                .build();
    }
}
