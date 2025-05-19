package com.hoangjunss.junsBank.mapper;


import com.hoangjunss.junsBank.dto.historyChangeStatusTransaction.HistoryChangeStatusTransactionCreateDTO;
import com.hoangjunss.junsBank.dto.historyChangeStatusTransaction.HistoryChangeStatusTransactionDTO;
import com.hoangjunss.junsBank.entity.user.HistoryChangeStatusTransaction;
import org.springframework.stereotype.Component;

@Component
public class HistoryChangeStatusTransactionMapper {
    public HistoryChangeStatusTransaction toEntity(HistoryChangeStatusTransactionCreateDTO dto) {
        return HistoryChangeStatusTransaction.builder()
             //   .oldStatus(dto.isOldStatus())
               // .newStatus(dto.isNewStatus())
              //  .updateDate(dto.getUpdateDate())
                .build();
    }

    public HistoryChangeStatusTransactionDTO toDTO(HistoryChangeStatusTransaction entity) {
        return HistoryChangeStatusTransactionDTO.builder()
                .id(entity.getId())
                .oldStatus(entity.isOldStatus())
                .newStatus(entity.isNewStatus())
                .updateDate(entity.getUpdateDate())
              //  .transactionId(entity.getTransaction().getId())
                .build();
    }
}
