package com.hoangjunss.junsBank.service.impl;


import com.hoangjunss.junsBank.dto.historyChangeStatusTransaction.HistoryChangeStatusTransactionCreateDTO;
import com.hoangjunss.junsBank.dto.historyChangeStatusTransaction.HistoryChangeStatusTransactionDTO;
import com.hoangjunss.junsBank.dto.historyChangeStatusTransaction.HistoryChangeStatusTransactionUpdateDTO;
import com.hoangjunss.junsBank.entity.user.HistoryChangeStatusTransaction;
import com.hoangjunss.junsBank.mapper.HistoryChangeStatusTransactionMapper;
import com.hoangjunss.junsBank.repository.HistoryChangeStatusTransactionRepository;
import com.hoangjunss.junsBank.service.HistoryChangeStatusTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HistoryChangeStatusTransactionServiceImpl implements HistoryChangeStatusTransactionService {

    private final HistoryChangeStatusTransactionRepository repository;
    private final HistoryChangeStatusTransactionMapper mapper;



    @Override
    public HistoryChangeStatusTransactionDTO createHistory(HistoryChangeStatusTransactionCreateDTO dto) {
        HistoryChangeStatusTransaction entity = mapper.toEntity(dto);
        HistoryChangeStatusTransaction saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    @Override
    public HistoryChangeStatusTransactionDTO updateHistory(HistoryChangeStatusTransactionUpdateDTO dto) {
      /*  HistoryChangeStatusTransaction entity = mapper.convertUpdateToEntity(dto);
        HistoryChangeStatusTransaction updated = repository.save(entity);
        return mapper.toHistoryChangeStatusTransactionDTO(updated);*/
        return  null;
    }

    @Override
    public HistoryChangeStatusTransactionDTO getHistoryById(Integer id) {
        HistoryChangeStatusTransaction entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("History not found: " + id));
        return mapper.toDTO(entity);
    }

    @Override
    public List<HistoryChangeStatusTransactionDTO> getAllHistories() {
        List<HistoryChangeStatusTransaction> list = repository.findAll();
        return list.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteHistory(Integer id) {
        repository.deleteById(id);
    }
}