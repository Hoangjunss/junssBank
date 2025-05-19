package com.hoangjunss.junsBank.service.impl;


import com.hoangjunss.junsBank.dto.historyChangeAccountPassword.HistoryChangePasswordAccountCreateDTO;
import com.hoangjunss.junsBank.dto.historyChangeAccountPassword.HistoryChangePasswordAccountDTO;
import com.hoangjunss.junsBank.dto.historyChangeAccountPassword.HistoryChangePasswordAccountUpdateDTO;
import com.hoangjunss.junsBank.entity.user.HistoryChangePasswordAccount;
import com.hoangjunss.junsBank.mapper.HistoryChangePasswordAccountMapper;
import com.hoangjunss.junsBank.repository.HistoryChangePasswordAccountRepository;
import com.hoangjunss.junsBank.service.HistoryChangePasswordAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HistoryChangePasswordAccountServiceImpl implements HistoryChangePasswordAccountService {

    private final HistoryChangePasswordAccountRepository repository;
    private final HistoryChangePasswordAccountMapper mapper;



    @Override
    public HistoryChangePasswordAccountDTO createHistory(HistoryChangePasswordAccountCreateDTO dto) {
        HistoryChangePasswordAccount entity = mapper.toEntity(dto);
        HistoryChangePasswordAccount saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    @Override
    public HistoryChangePasswordAccountDTO updateHistory(HistoryChangePasswordAccountUpdateDTO dto) {
       /* HistoryChangePasswordAccount entity = mapper.convertUpdateToEntity(dto);
        HistoryChangePasswordAccount updated = repository.save(entity);
        return mapper.toHistoryChangePasswordAccountDTO(updated);*/
        return null;
    }

    @Override
    public HistoryChangePasswordAccountDTO getHistoryById(Integer id) {
        HistoryChangePasswordAccount entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("History not found: " + id));
        return mapper.toDTO(entity);
    }

    @Override
    public List<HistoryChangePasswordAccountDTO> getAllHistories() {
        List<HistoryChangePasswordAccount> list = repository.findAll();
        return list.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteHistory(Integer id) {
        repository.deleteById(id);
    }
}